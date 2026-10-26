/*
 * Ghidra headless decompilation
 * program   : libjni_stfaceunlock_api.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libjni_stfaceunlock_api.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : Java_.*|FaceDetectCallBack|register_fields|find_fields|java_stimage_to_st_image_t|java_rect_to_st_rect_t|st_detect_to_java_face_detected_info|st_faceid_info_array_to_java_faceidinfo
 * max funcs : unlimited
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: st_detect_to_java_face_detected_info
 * Address : 001023b0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

jobject st_detect_to_java_face_detected_info(JNIEnv *env,P_ST_DETECT detect_result)

{
  jobject p_Var1;
  
  p_Var1 = (jobject)(*(code *)PTR_st_detect_to_java_face_detected_info_00105ed0)();
  return p_Var1;
}



/* ------------------------------------------------------------------
 * Function: st_faceid_info_array_to_java_faceidinfo
 * Address : 001024d0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

jobjectArray
st_faceid_info_array_to_java_faceidinfo
          (JNIEnv *env,st_face_id_info_t *p_faceid_info_array,int face_idinfo_count,int version)

{
  jobjectArray p_Var1;
  
  p_Var1 = (jobjectArray)
           (*(code *)PTR_st_faceid_info_array_to_java_faceidinfo_00105f60)
                     (env,p_faceid_info_array,face_idinfo_count,version);
  return p_Var1;
}



/* ------------------------------------------------------------------
 * Function: register_fields
 * Address : 001024f0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

int register_fields(JNIEnv *env)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_register_fields_00105f70)((int)env);
  return iVar1;
}



/* ------------------------------------------------------------------
 * Function: java_rect_to_st_rect_t
 * Address : 00102510
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

st_rect_t java_rect_to_st_rect_t(JNIEnv *env,jobject jrect)

{
  st_rect_t sVar1;
  
  sVar1 = (st_rect_t)(*(code *)PTR_java_rect_to_st_rect_t_00105f80)();
  return sVar1;
}



/* ------------------------------------------------------------------
 * Function: java_stimage_to_st_image_t
 * Address : 00102550
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

jbyteArray java_stimage_to_st_image_t(JNIEnv *env,jobject java_stimage,st_image_t *image)

{
  jbyteArray p_Var1;
  
  p_Var1 = (jbyteArray)(*(code *)PTR_java_stimage_to_st_image_t_00105fa0)();
  return p_Var1;
}



/* ------------------------------------------------------------------
 * Function: find_fields
 * Address : 001025cc
 * Size    : 0xc4 bytes
 * ------------------------------------------------------------------ */

int find_fields(JNIEnv *env,field *fields,int count)

{
  jfieldID **ppp_Var1;
  jclass p_Var2;
  jfieldID p_Var3;
  ulong uVar4;
  
                    /* Unresolved local var: int i@[???] */
  if (0 < count) {
    uVar4 = (ulong)(uint)count;
    do {
                    /* Unresolved local var: field * f@[???]
                       Unresolved local var: jclass clazz@[???]
                       Unresolved local var: jfieldID field@[???] */
      p_Var2 = (*env->functions->FindClass)(env,fields->class_name);
      if (p_Var2 == (jclass)0x0) {
        __android_log_print(6,"FaceAuth","Can\'t find %s",fields->class_name);
        return -1;
      }
      p_Var3 = (*env->functions->GetFieldID)(env,p_Var2,fields->field_name,fields->field_type);
      if (p_Var3 == (jfieldID)0x0) {
        __android_log_print(6,"FaceAuth","Can\'t find %s.%s",fields->class_name,fields->field_name);
        return -1;
      }
      ppp_Var1 = &fields->jfield;
      uVar4 = uVar4 - 1;
      fields = fields + 1;
      **ppp_Var1 = p_Var3;
    } while (uVar4 != 0);
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: register_fields
 * Address : 00102690
 * Size    : 0x2d8 bytes
 * ------------------------------------------------------------------ */

int register_fields(JNIEnv *env)

{
  undefined8 *puVar1;
  char *pcVar2;
  undefined *puVar3;
  jclass p_Var4;
  jfieldID p_Var5;
  jobject p_Var6;
  jmethodID p_Var7;
  char *pcVar8;
  long lVar9;
  
                    /* Unresolved local var: jclass clazz@[???]
                       Unresolved local var: field[13] fields_to_find@[???] */
  lVar9 = 0;
  while( true ) {
                    /* Unresolved local var: int i@[???]
                       Unresolved local var: field * f@[???]
                       Unresolved local var: jclass clazz@[???]
                       Unresolved local var: jfieldID field@[???] */
    pcVar8 = *(char **)((long)&PTR_s_com_ts_faceapi_model_StRect_00105ac8 + lVar9);
    p_Var4 = (*env->functions->FindClass)(env,pcVar8);
    if (p_Var4 == (jclass)0x0) {
      __android_log_print(6,"FaceAuth","Can\'t find %s",pcVar8);
      return -1;
    }
    pcVar2 = *(char **)((long)&PTR_DAT_00105ad0 + lVar9);
    p_Var5 = (*env->functions->GetFieldID)
                       (env,p_Var4,pcVar2,*(char **)((long)&PTR_DAT_00105ad8 + lVar9));
    if (p_Var5 == (jfieldID)0x0) break;
    puVar1 = (undefined8 *)((long)&PTR_fields_00105ae0 + lVar9);
    lVar9 = lVar9 + 0x20;
    *(jfieldID *)*puVar1 = p_Var5;
    if (lVar9 == 0x1a0) {
      p_Var4 = (*env->functions->FindClass)(env,"com/ts/faceapi/model/StRect");
      p_Var6 = (*env->functions->NewGlobalRef)(env,(jobject)p_Var4);
      *(jobject *)PTR_mRectClass_00105fe0 = p_Var6;
      p_Var7 = (*env->functions->GetMethodID)(env,p_Var4,"<init>","()V");
      puVar3 = PTR_fields_00105fc8;
      *(jmethodID *)(PTR_fields_00105fc8 + 0x20) = p_Var7;
      if (p_Var7 == (jmethodID)0x0) {
        pcVar8 = "Can\'t find com/ts/faceapi/model/StRect.StRect()";
      }
      else {
        p_Var4 = (*env->functions->FindClass)(env,"com/ts/faceauth/data/FaceIDInfo");
        p_Var6 = (*env->functions->NewGlobalRef)(env,(jobject)p_Var4);
        *(jobject *)PTR_mFaceIDInfoClass_00105fd0 = p_Var6;
        p_Var7 = (*env->functions->GetMethodID)(env,p_Var4,"<init>","()V");
        *(jmethodID *)(puVar3 + 0x60) = p_Var7;
        if (p_Var7 == (jmethodID)0x0) {
          pcVar8 = "Can\'t find com/ts/faceauth/data/FaceIDInfo.FaceIDInfo()";
        }
        else {
          p_Var4 = (*env->functions->FindClass)(env,"com/ts/faceauth/data/FaceDetectedInfo");
          p_Var6 = (*env->functions->NewGlobalRef)(env,(jobject)p_Var4);
          *(jobject *)PTR_mFaceDetectedInfoClass_00105fe8 = p_Var6;
          p_Var7 = (*env->functions->GetMethodID)(env,p_Var4,"<init>","()V");
          *(jmethodID *)(puVar3 + 0x78) = p_Var7;
          if (p_Var7 == (jmethodID)0x0) {
            pcVar8 = "Can\'t find com/ts/faceauth/data/FaceDetectedInfo.FaceDetectedInfo()";
          }
          else {
            p_Var4 = (*env->functions->FindClass)(env,"com/ts/faceauth/FaceAuthNative");
            p_Var6 = (*env->functions->NewGlobalRef)(env,(jobject)p_Var4);
            *(jobject *)PTR_mFaceAuthNativeClass_00105ff0 = p_Var6;
            p_Var7 = (*env->functions->GetStaticMethodID)
                               (env,p_Var4,"onFaceDetected",
                                "(Lcom/ts/faceauth/data/FaceDetectedInfo;)V");
            *(jmethodID *)(puVar3 + 0x80) = p_Var7;
            if (p_Var7 != (jmethodID)0x0) {
              return 0;
            }
            pcVar8 = "Can\'t find com/ts/faceauth/FaceAuthNative onFaceDetected";
          }
        }
      }
      __android_log_print(6,"FaceAuth",pcVar8);
      return -1;
    }
  }
  __android_log_print(6,"FaceAuth","Can\'t find %s.%s",pcVar8,pcVar2);
  return -1;
}



/* ------------------------------------------------------------------
 * Function: st_faceid_info_array_to_java_faceidinfo
 * Address : 00102968
 * Size    : 0x108 bytes
 * ------------------------------------------------------------------ */

jobjectArray
st_faceid_info_array_to_java_faceidinfo
          (JNIEnv *env,st_face_id_info_t *p_faceid_info_array,int face_idinfo_count,int version)

{
  undefined *puVar1;
  undefined *puVar2;
  jobjectArray p_Var3;
  jobject p_Var4;
  ulong uVar5;
  int *piVar6;
  
                    /* Unresolved local var: jobjectArray faceid_info_array@[???] */
  puVar2 = PTR_mFaceIDInfoClass_00105fd0;
  p_Var3 = (*env->functions->NewObjectArray)
                     (env,face_idinfo_count,*(jclass *)PTR_mFaceIDInfoClass_00105fd0,(jobject)0x0);
  puVar1 = PTR_fields_00105fc8;
                    /* Unresolved local var: int j@[???] */
  if (0 < face_idinfo_count) {
    uVar5 = 0;
    piVar6 = &p_faceid_info_array->feature_count;
    do {
                    /* Unresolved local var: jobject faceidinfo@[???]
                       Unresolved local var: int feature_count@[???] */
      p_Var4 = _JNIEnv::NewObject(env,*(jclass *)puVar2,*(jmethodID *)(puVar1 + 0x60));
      (*env->functions->SetIntField)
                (env,p_Var4,*(jfieldID *)(puVar1 + 0x50),
                 ((st_face_id_info_t *)(piVar6 + -1))->face_id);
      (*env->functions->SetIntField)(env,p_Var4,*(jfieldID *)(puVar1 + 0x58),*piVar6);
      (*env->functions->SetObjectArrayElement)(env,p_Var3,(jsize)uVar5,p_Var4);
      (*env->functions->DeleteLocalRef)(env,p_Var4);
      uVar5 = uVar5 + 1;
      piVar6 = piVar6 + 4;
    } while ((uint)face_idinfo_count != uVar5);
  }
  return p_Var3;
}



/* ------------------------------------------------------------------
 * Function: java_rect_to_st_rect_t
 * Address : 00102b0c
 * Size    : 0xb0 bytes
 * ------------------------------------------------------------------ */

st_rect_t java_rect_to_st_rect_t(JNIEnv *env,jobject jrect)

{
  undefined *puVar1;
  jint jVar2;
  jint jVar3;
  jint jVar4;
  st_rect_t sVar5;
  
                    /* Unresolved local var: st_rect_t rect@[???] */
  puVar1 = PTR_fields_00105fc8;
  sVar5.left = (*env->functions->GetIntField)(env,jrect,*(jfieldID *)PTR_fields_00105fc8);
  jVar2 = (*env->functions->GetIntField)(env,jrect,*(jfieldID *)(puVar1 + 8));
  jVar3 = (*env->functions->GetIntField)(env,jrect,*(jfieldID *)(puVar1 + 0x10));
  jVar4 = (*env->functions->GetIntField)(env,jrect,*(jfieldID *)(puVar1 + 0x18));
  sVar5.top = jVar2;
  sVar5.bottom = jVar4;
  sVar5.right = jVar3;
  return sVar5;
}



/* ------------------------------------------------------------------
 * Function: st_detect_to_java_face_detected_info
 * Address : 00102bbc
 * Size    : 0x13c bytes
 * ------------------------------------------------------------------ */

jobject st_detect_to_java_face_detected_info(JNIEnv *env,P_ST_DETECT detect_result)

{
  undefined *puVar1;
  jobject p_Var2;
  jobject p_Var3;
  jboolean jVar4;
  
                    /* Unresolved local var: jobject face_detected_info@[???] */
  puVar1 = PTR_fields_00105fc8;
  p_Var2 = _JNIEnv::NewObject(env,*(jclass *)PTR_mFaceDetectedInfoClass_00105fe8,
                              *(jmethodID *)(PTR_fields_00105fc8 + 0x78));
  if (detect_result->has_face == false) {
    jVar4 = '\0';
  }
  else {
                    /* Unresolved local var: jobject rect@[???] */
    p_Var3 = _JNIEnv::NewObject(env,*(jclass *)PTR_mRectClass_00105fe0,*(jmethodID *)(puVar1 + 0x20)
                               );
    (*env->functions->SetIntField)(env,p_Var3,*(jfieldID *)puVar1,(detect_result->face_rect).left);
    (*env->functions->SetIntField)
              (env,p_Var3,*(jfieldID *)(puVar1 + 8),(detect_result->face_rect).top);
    (*env->functions->SetIntField)
              (env,p_Var3,*(jfieldID *)(puVar1 + 0x10),(detect_result->face_rect).right);
    (*env->functions->SetIntField)
              (env,p_Var3,*(jfieldID *)(puVar1 + 0x18),(detect_result->face_rect).bottom);
    (*env->functions->SetObjectField)(env,p_Var2,*(jfieldID *)(puVar1 + 0x70),p_Var3);
    (*env->functions->DeleteLocalRef)(env,p_Var3);
    jVar4 = detect_result->has_face;
  }
  (*env->functions->SetBooleanField)(env,p_Var2,*(jfieldID *)(puVar1 + 0x68),jVar4);
  return p_Var2;
}



/* ------------------------------------------------------------------
 * Function: java_stimage_to_st_image_t
 * Address : 00102cf8
 * Size    : 0x158 bytes
 * ------------------------------------------------------------------ */

jbyteArray java_stimage_to_st_image_t(JNIEnv *env,jobject java_stimage,st_image_t *image)

{
  undefined *puVar1;
  st_pixel_format sVar2;
  jint jVar3;
  uchar *puVar4;
  jobject p_Var5;
  
                    /* Unresolved local var: jobject obj@[???]
                       Unresolved local var: jbyteArray data_array@[???]
                       Unresolved local var: int format@[???] */
  puVar1 = PTR_fields_00105fc8;
  p_Var5 = (jobject)0x0;
  if ((java_stimage != (jobject)0x0) && (image != (st_image_t *)0x0)) {
    p_Var5 = (*env->functions->GetObjectField)
                       (env,java_stimage,*(jfieldID *)(PTR_fields_00105fc8 + 0x28));
    if (p_Var5 != (jobject)0x0) {
                    /* Unresolved local var: jbyte * image_data@[???] */
      puVar4 = (uchar *)(*env->functions->GetByteArrayElements)
                                  (env,(jbyteArray)p_Var5,(jboolean *)0x0);
      image->data = puVar4;
    }
    sVar2 = (*env->functions->GetIntField)(env,java_stimage,*(jfieldID *)(puVar1 + 0x30));
    image->pixel_format = sVar2;
    __android_log_print(3,"FaceAuth","image format is %d",sVar2);
    jVar3 = (*env->functions->GetIntField)(env,java_stimage,*(jfieldID *)(puVar1 + 0x38));
    image->width = jVar3;
    __android_log_print(3,"FaceAuth","image width is %d",jVar3);
    jVar3 = (*env->functions->GetIntField)(env,java_stimage,*(jfieldID *)(puVar1 + 0x40));
    image->height = jVar3;
    __android_log_print(3,"FaceAuth","image height is %d",jVar3);
    jVar3 = (*env->functions->GetIntField)(env,java_stimage,*(jfieldID *)(puVar1 + 0x48));
    image->stride = jVar3;
    __android_log_print(3,"FaceAuth","image stride is %d",jVar3);
  }
  return (jbyteArray)p_Var5;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_getAlgoInfo
 * Address : 00102e50
 * Size    : 0x50 bytes
 * ------------------------------------------------------------------ */

jstring Java_com_ts_faceauth_FaceAuthNative_getAlgoInfo(JNIEnv *env,jobject obj)

{
  char *pcVar1;
  jstring p_Var2;
  
                    /* Unresolved local var: jstring algoInfo@[???]
                       Unresolved local var: char * algo_info@[???] */
  pcVar1 = (char *)st_face_algorithm_info();
  __android_log_print(3,"FaceAuth","algoinfo  %s",pcVar1);
                    /* WARNING: Could not recover jumptable at 0x00102e9c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  p_Var2 = (*env->functions->NewStringUTF)(env,pcVar1);
  return p_Var2;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_getSDKVersion
 * Address : 00102ea0
 * Size    : 0x50 bytes
 * ------------------------------------------------------------------ */

jstring Java_com_ts_faceauth_FaceAuthNative_getSDKVersion(JNIEnv *env,jobject obj)

{
  char *pcVar1;
  jstring p_Var2;
  
                    /* Unresolved local var: jstring sdkVersion@[???]
                       Unresolved local var: char * sdk_version@[???] */
  pcVar1 = (char *)st_auth_get_version();
  __android_log_print(3,"FaceAuth","sdk version is %s",pcVar1);
                    /* WARNING: Could not recover jumptable at 0x00102eec. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  p_Var2 = (*env->functions->NewStringUTF)(env,pcVar1);
  return p_Var2;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_initLicense
 * Address : 00102ef0
 * Size    : 0x74 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_initLicense(JNIEnv *env,jobject obj,jstring _license)

{
  jint jVar1;
  char *pcVar2;
  
                    /* Unresolved local var: char * license@[???]
                       Unresolved local var: st_result_t result@[???] */
  if (_license != (jstring)0x0) {
    pcVar2 = (*env->functions->GetStringUTFChars)(env,_license,(jboolean *)0x0);
    jVar1 = st_face_init_license();
    if (pcVar2 != (char *)0x0) {
      (*env->functions->ReleaseStringUTFChars)(env,_license,pcVar2);
    }
    return jVar1;
  }
  jVar1 = st_face_init_license(0);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_createFaceAuth
 * Address : 00102f64
 * Size    : 0xec bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_createFaceAuth
               (JNIEnv *env,jobject obj,jstring j_dir_path,jint config,jintArray _resultcode)

{
  jint jVar1;
  char *pcVar2;
  jint *pjVar3;
  
                    /* Unresolved local var: char * dir_path@[???]
                       Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???] */
  if (j_dir_path == (jstring)0x0) {
    pcVar2 = (char *)0x0;
  }
  else {
    pcVar2 = (*env->functions->GetStringUTFChars)(env,j_dir_path,(jboolean *)0x0);
  }
  if (_resultcode == (jintArray)0x0) {
    st_auth_init(pcVar2,config);
  }
  else {
    pjVar3 = (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    jVar1 = st_auth_init(pcVar2,config);
    if (pjVar3 != (jint *)0x0) {
      *pjVar3 = jVar1;
      (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar3,0);
    }
  }
  if (pcVar2 == (char *)0x0) {
    return;
  }
                    /* WARNING: Could not recover jumptable at 0x00103020. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*env->functions->ReleaseStringUTFChars)(env,j_dir_path,pcVar2);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_releaseFaceAuth
 * Address : 00103050
 * Size    : 0x54 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_releaseFaceAuth(JNIEnv *env,jobject obj)

{
  undefined *puVar1;
  
  st_auth_deinit();
  puVar1 = PTR_g_so_handle_00105fd8;
  if (*(long *)PTR_g_so_handle_00105fd8 != 0) {
    __android_log_print(3,"FaceAuth","dlclose the so for tee for the handle is not null");
    dlclose(*(undefined8 *)puVar1);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setRoi
 * Address : 001030a4
 * Size    : 0xd4 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setRoi
               (JNIEnv *env,jobject obj,jobject facerect,jintArray _resultcode)

{
  long lVar1;
  jint jVar2;
  jint *pjVar3;
  undefined1 auVar4 [16];
  st_rect_t local_58;
  long local_48;
  
                    /* Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???]
                       Unresolved local var: st_rect_t face_rect@[???] */
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  if (_resultcode == (jintArray)0x0) {
    pjVar3 = (jint *)0x0;
  }
  else {
    auVar4 = (undefined1  [16])
             (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    obj = auVar4._8_8_;
    pjVar3 = auVar4._0_8_;
  }
  if (facerect == (jobject)0x0) {
    jVar2 = st_auth_set_roi(0,obj);
  }
  else {
    local_58 = java_rect_to_st_rect_t(env,facerect);
    jVar2 = st_auth_set_roi(&local_58);
  }
  if (pjVar3 != (jint *)0x0) {
    *pjVar3 = jVar2;
    (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar3,0);
  }
  if (*(long *)(lVar1 + 0x28) != local_48) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setIdentifier
 * Address : 00103178
 * Size    : 0xe0 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setIdentifier
               (JNIEnv *env,jobject obj,jstring j_identifier,jintArray _resultcode)

{
  jint jVar1;
  jint *pjVar2;
  char *pcVar3;
  undefined1 auVar4 [16];
  
                    /* Unresolved local var: char * identifier@[???]
                       Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???] */
  if (j_identifier == (jstring)0x0) {
    pcVar3 = (char *)0x0;
  }
  else {
    auVar4 = (undefined1  [16])
             (*env->functions->GetStringUTFChars)(env,j_identifier,(jboolean *)0x0);
    obj = auVar4._8_8_;
    pcVar3 = auVar4._0_8_;
  }
  if (_resultcode == (jintArray)0x0) {
    st_auth_set_identifier(pcVar3,obj);
  }
  else {
    pjVar2 = (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    jVar1 = st_auth_set_identifier(pcVar3);
    if (pjVar2 != (jint *)0x0) {
      *pjVar2 = jVar1;
      (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar2,0);
    }
  }
  if (pcVar3 == (char *)0x0) {
    return;
  }
                    /* WARNING: Could not recover jumptable at 0x0010322c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*env->functions->ReleaseStringUTFChars)(env,j_identifier,pcVar3);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setOrientation
 * Address : 00103258
 * Size    : 0x94 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setOrientation
               (JNIEnv *env,jobject obj,jint orientation,jintArray _resultcode)

{
  jint jVar1;
  jint *pjVar2;
  
                    /* Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???] */
  if (_resultcode == (jintArray)0x0) {
    st_auth_set_face_orientation(orientation);
    return;
  }
  pjVar2 = (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
  jVar1 = st_auth_set_face_orientation(orientation);
  if (pjVar2 != (jint *)0x0) {
    *pjVar2 = jVar1;
                    /* WARNING: Could not recover jumptable at 0x001032c4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar2,0);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setEnrollNumberForFace
 * Address : 001032ec
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_setEnrollNumberForFace(JNIEnv *env,jobject obj,jint number)

{
  jint jVar1;
  
  jVar1 = st_auth_set_faceid_image_number(number);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_enroll
 * Address : 001032f4
 * Size    : 0x12c bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_enroll
               (JNIEnv *env,jobject obj,jobject stimage,jint config,jintArray _resultcode)

{
  long lVar1;
  jint jVar2;
  code *pcVar3;
  jbyteArray p_Var4;
  jint *pjVar5;
  undefined1 auVar6 [16];
  jint local_64;
  st_image_t local_60;
  long local_48;
  
                    /* Unresolved local var: int user_id@[???]
                       Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???]
                       Unresolved local var: st_image_t enroll_image@[???]
                       Unresolved local var: jbyteArray j_image_data@[???] */
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  local_60.pixel_format = ST_PIX_FMT_GRAY8;
  local_60.width = 0;
  local_60.height = 0;
  local_60.stride = 0;
  local_60.data = (uchar *)0x0;
  local_64 = -1;
  if (_resultcode == (jintArray)0x0) {
    pjVar5 = (jint *)0x0;
  }
  else {
    auVar6 = (undefined1  [16])
             (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    obj = auVar6._8_8_;
    pjVar5 = auVar6._0_8_;
  }
  if (*PTR_g_enable_facedetect_callback_00105ff8 == '\0') {
    pcVar3 = (code *)0x0;
  }
  else {
    pcVar3 = FaceDetectCallBack;
  }
  st_auth_set_facedetect_callback(pcVar3,obj);
  p_Var4 = java_stimage_to_st_image_t(env,stimage,&local_60);
  jVar2 = st_auth_enroll(&local_60,config,&local_64);
  if (p_Var4 != (jbyteArray)0x0) {
    (*env->functions->ReleaseByteArrayElements)(env,p_Var4,(jbyte *)local_60.data,0);
  }
  if (pjVar5 != (jint *)0x0) {
    *pjVar5 = jVar2;
    (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar5,0);
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return local_64;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: FaceDetectCallBack
 * Address : 00103420
 * Size    : 0x110 bytes
 * ------------------------------------------------------------------ */

void FaceDetectCallBack(P_ST_DETECT p_detect)

{
  long lVar1;
  bool bVar2;
  undefined *puVar3;
  jint jVar4;
  jobject p_Var5;
  JNIEnv *local_50;
  long local_48;
  
                    /* Unresolved local var: bool isAttached@[???]
                       Unresolved local var: int getEnvStatus@[???]
                       Unresolved local var: JNIEnv * env@[???]
                       Unresolved local var: int status@[???] */
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  jVar4 = (*gJavaVM->functions->GetEnv)(gJavaVM,&local_50,0x10006);
  if (jVar4 == -2) {
    jVar4 = (*gJavaVM->functions->AttachCurrentThread)(gJavaVM,&local_50,(void *)0x0);
    if (jVar4 < 0) {
      __android_log_print(6,"FaceAuth","callbackFun: failed to attach current thread!");
    }
    bVar2 = true;
  }
  else {
    bVar2 = false;
  }
  puVar3 = PTR_fields_00105fc8;
  if (*(long *)(PTR_fields_00105fc8 + 0x80) != 0) {
                    /* Unresolved local var: jobject j_face_detectedinfo@[???] */
    p_Var5 = st_detect_to_java_face_detected_info(local_50,p_detect);
    _JNIEnv::CallStaticVoidMethod
              (local_50,*(jclass *)PTR_mFaceAuthNativeClass_00105ff0,*(jmethodID *)(puVar3 + 0x80),
               p_Var5);
  }
  if (bVar2) {
    (*gJavaVM->functions->DetachCurrentThread)(gJavaVM);
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_auth
 * Address : 00103530
 * Size    : 0x19c bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_auth
               (JNIEnv *env,jobject obj,jobject stimage,jint config,jintArray faceids,
               jintArray _resultcode)

{
  long lVar1;
  jint jVar2;
  jint *pjVar3;
  code *pcVar4;
  jbyteArray p_Var5;
  jint *pjVar6;
  ulong uVar7;
  undefined1 auVar8 [16];
  st_image_t local_80;
  long local_68;
  
                    /* Unresolved local var: int faceid_list_length@[???]
                       Unresolved local var: int * faceid_list@[???]
                       Unresolved local var: st_result_t result@[???]
                       Unresolved local var: jint * result_code@[???]
                       Unresolved local var: st_image_t auth_image@[???]
                       Unresolved local var: jbyteArray j_image_data@[???] */
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  local_80.pixel_format = ST_PIX_FMT_GRAY8;
  local_80.width = 0;
  local_80.height = 0;
  local_80.stride = 0;
  local_80.data = (uchar *)0x0;
  if (_resultcode == (jintArray)0x0) {
    pjVar6 = (jint *)0x0;
  }
  else {
    auVar8 = (undefined1  [16])
             (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    obj = auVar8._8_8_;
    pjVar6 = auVar8._0_8_;
  }
  if (faceids == (jintArray)0x0) {
    pjVar3 = (jint *)0x0;
    uVar7 = 0;
  }
  else {
    pjVar3 = (*env->functions->GetIntArrayElements)(env,faceids,(jboolean *)0x0);
    auVar8 = (undefined1  [16])(*env->functions->GetArrayLength)(env,(jarray)faceids);
    obj = auVar8._8_8_;
    uVar7 = auVar8._0_8_ & 0xffffffff;
  }
  if (*PTR_g_enable_facedetect_callback_00105ff8 == '\0') {
    pcVar4 = (code *)0x0;
  }
  else {
    pcVar4 = FaceDetectCallBack;
  }
  st_auth_set_facedetect_callback(pcVar4,obj);
  p_Var5 = java_stimage_to_st_image_t(env,stimage,&local_80);
  jVar2 = st_auth_unlock(&local_80,config,pjVar3,uVar7);
  if (p_Var5 != (jbyteArray)0x0) {
    (*env->functions->ReleaseByteArrayElements)(env,p_Var5,(jbyte *)local_80.data,0);
  }
  if (pjVar3 != (jint *)0x0) {
    (*env->functions->ReleaseIntArrayElements)(env,faceids,pjVar3,0);
  }
  if (pjVar6 != (jint *)0x0) {
    *pjVar6 = jVar2;
    (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar6,0);
  }
  if (*(long *)(lVar1 + 0x28) != local_68) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_deleteFace
 * Address : 001036cc
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_deleteFace(JNIEnv *env,jobject obj,jint faceid)

{
  jint jVar1;
  
                    /* Unresolved local var: int result@[???] */
  jVar1 = st_auth_delete_face(faceid);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_deleteAllFace
 * Address : 001036d4
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_deleteAllFace(JNIEnv *env,jobject obj)

{
  jint jVar1;
  
                    /* Unresolved local var: int result@[???] */
  jVar1 = (*(code *)PTR_st_auth_delete_all_face_00105f50)((int)env);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_resetFaceAuth
 * Address : 001036d8
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_resetFaceAuth(JNIEnv *env,jobject obj)

{
  jint jVar1;
  
                    /* Unresolved local var: int result@[???] */
  jVar1 = (*(code *)PTR_st_auth_reset_00105e80)((int)env);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_evaluate
 * Address : 001036dc
 * Size    : 0xc4 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_evaluate
               (JNIEnv *env,jobject obj,jstring inputPath,jstring outputPath,jint imageFormat,
               jint width,jint height,jint orientation,jint faceauth_mode)

{
  jint jVar1;
  char *pcVar2;
  char *pcVar3;
  
                    /* Unresolved local var: char * output_path@[???]
                       Unresolved local var: char * input_path@[???]
                       Unresolved local var: int result@[???] */
  if ((inputPath != (jstring)0x0) && (outputPath != (jstring)0x0)) {
    pcVar2 = (*env->functions->GetStringUTFChars)(env,inputPath,(jboolean *)0x0);
    pcVar3 = (*env->functions->GetStringUTFChars)(env,outputPath,(jboolean *)0x0);
    if ((pcVar2 != (char *)0x0) && (pcVar3 != (char *)0x0)) {
      jVar1 = st_auth_evaluate(pcVar2,pcVar3,imageFormat,width,height,orientation,faceauth_mode);
      return jVar1;
    }
  }
  return -1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setFaceAuthMode
 * Address : 001037a0
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_setFaceAuthMode(JNIEnv *env,jobject obj,jint mode)

{
  jint jVar1;
  
  jVar1 = st_auth_set_faceauth_mode(mode);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setAreaCode
 * Address : 001037a8
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_setAreaCode(JNIEnv *env,jobject obj,jint area_code)

{
  jint jVar1;
  
  jVar1 = st_auth_set_threshold_by_international_code(area_code);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_getFaceIDInfoInTemplate
 * Address : 001037b0
 * Size    : 0xe8 bytes
 * ------------------------------------------------------------------ */

jobjectArray
Java_com_ts_faceauth_FaceAuthNative_getFaceIDInfoInTemplate
          (JNIEnv *env,jobject obj,jintArray _resultcode)

{
  long lVar1;
  int iVar2;
  jint *pjVar3;
  jobjectArray p_Var4;
  undefined1 auVar5 [16];
  int *local_50;
  long local_48;
  
                    /* Unresolved local var: st_template_info_t * template_info@[???]
                       Unresolved local var: jint * result_code@[???]
                       Unresolved local var: jobjectArray faceidinfo_array@[???]
                       Unresolved local var: st_result_t result@[???] */
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  local_50 = (int *)0x0;
  if (_resultcode == (jintArray)0x0) {
    pjVar3 = (jint *)0x0;
  }
  else {
    auVar5 = (undefined1  [16])
             (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    obj = auVar5._8_8_;
    pjVar3 = auVar5._0_8_;
  }
  iVar2 = st_auth_get_template_info(&local_50,obj);
  p_Var4 = (jobjectArray)0x0;
  if ((iVar2 == 0) && (local_50 != (int *)0x0)) {
    p_Var4 = st_faceid_info_array_to_java_faceidinfo
                       (env,*(st_face_id_info_t **)(local_50 + 4),*local_50,local_50[1]);
  }
  if (pjVar3 != (jint *)0x0) {
    *pjVar3 = iVar2;
    (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar3,0);
  }
  if (local_50 != (int *)0x0) {
    st_auth_release_template_info();
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return p_Var4;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setTeeCommunicationFunc
 * Address : 00103898
 * Size    : 0x88 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_setTeeCommunicationFunc(JNIEnv *env,jobject obj)

{
  jint jVar1;
  long lVar2;
  undefined8 uVar3;
  char *pcVar4;
  
                    /* Unresolved local var: st_result_t result@[???] */
  lVar2 = dlopen("libst_client.so",2);
  *(long *)PTR_g_so_handle_00105fd8 = lVar2;
  if (lVar2 == 0) {
    uVar3 = dlerror();
    pcVar4 = "no so for set tee func, error is %s";
  }
  else {
                    /* Unresolved local var: TEECommunicationFunc teeCommunicationFunc@[???] */
    lVar2 = dlsym(lVar2,"tee_communication_func");
    if (lVar2 != 0) {
      jVar1 = st_auth_set_tee_communication_func();
      return jVar1;
    }
    uVar3 = dlerror();
    pcVar4 = "no communication func, error is %s";
  }
  __android_log_print(6,"FaceAuth",pcVar4,uVar3);
  return -4;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setTeeCommunicationFuncAddr
 * Address : 00103920
 * Size    : 0x38 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_setTeeCommunicationFuncAddr
               (JNIEnv *env,jobject obj,jlong teeCommunicationFunc)

{
  jint jVar1;
  
                    /* Unresolved local var: st_result_t result@[???] */
  __android_log_print(3,"FaceAuth","setTeeCommunicationFuncAddr");
  jVar1 = st_auth_set_tee_communication_func(teeCommunicationFunc);
  return jVar1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setLogLevel
 * Address : 00103958
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setLogLevel(JNIEnv *env,jobject obj,jint log_level)

{
  st_face_open_log(log_level);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setGPUPriority
 * Address : 00103960
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setGPUPriority(JNIEnv *env,jobject obj,jint priority)

{
  st_auth_set_gpu_priority(priority);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_setStandByMode
 * Address : 00103968
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_setStandByMode(JNIEnv *env,jobject obj)

{
  (*(code *)PTR_st_auth_set_standby_mode_00105ea8)();
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_getLastUnlockID
 * Address : 0010396c
 * Size    : 0xb0 bytes
 * ------------------------------------------------------------------ */

jint Java_com_ts_faceauth_FaceAuthNative_getLastUnlockID
               (JNIEnv *env,jobject obj,jintArray _resultcode)

{
  long lVar1;
  jint jVar2;
  jint *pjVar3;
  jint local_3c;
  long local_38;
  
                    /* Unresolved local var: int unlock_id@[???]
                       Unresolved local var: jint * result_code@[???]
                       Unresolved local var: st_result_t result@[???] */
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  local_3c = -1;
  if (_resultcode == (jintArray)0x0) {
    st_auth_get_last_unlock_id(&local_3c);
  }
  else {
    pjVar3 = (*env->functions->GetIntArrayElements)(env,_resultcode,(jboolean *)0x0);
    jVar2 = st_auth_get_last_unlock_id(&local_3c);
    if (pjVar3 != (jint *)0x0) {
      *pjVar3 = jVar2;
      (*env->functions->ReleaseIntArrayElements)(env,_resultcode,pjVar3,0);
    }
  }
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return local_3c;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_ts_faceauth_FaceAuthNative_enableFaceDetectCallback
 * Address : 00103a1c
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

void Java_com_ts_faceauth_FaceAuthNative_enableFaceDetectCallback
               (JNIEnv *env,jobject obj,jboolean isEnable)

{
  *PTR_g_enable_facedetect_callback_00105ff8 = isEnable != '\0';
  return;
}



