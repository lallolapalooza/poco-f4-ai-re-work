varying vec2 texc;
varying vec2 texf;

float blendSoftLight(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 red_texv = texf - offset;
    red_texv.x /= wh.x;
    red_texv.y /= wh.y;

    float gray_rate_lip = 1.0;
    float gray_rate_face = 1.0;
    vec3 grat_temp = vec3(0.3,0.59,0.11);
    float gray = dot(outcolor,grat_temp);

    vec3 o_color = origin_color.rgb;
    vec4 mask_color = texture2D(tex_improvered, red_texv);
    vec4 mask_color2 = texture2D(tex_improvered2,red_texv);
    vec3 lip_color = vec3(0.0);
    vec3 face_color = vec3(0.0);
    float face_alpha = 0.0;
    if(red_type < 0.5){
      //默认
      lip_color = red_normal_lip;
      face_color = red_normal_face;
      face_alpha = mask_color.g;
      /* gray_rate_lip = texture2D(tex_curve_mask_lip,vec2(gray,0.0)).r;
      gray_rate_lip = clamp(gray_rate_lip,0.0,1.0);
      gray_rate_face = texture2D(tex_curve_mask_face,vec2(gray,0.0)).r;
      gray_rate_face = clamp(gray_rate_face,0.0,1.0); */
    } else if(red_type < 1.5){
      //经典
      lip_color = red_jingdian_lip;
      face_color = red_jingdian_face;
      face_alpha = mask_color2.b;
    } else if(red_type < 2.5){
      //质感
      lip_color = red_zhigan_lip;
      face_color = red_zhigan_face;
      face_alpha = mask_color.b;
    } else if(red_type < 3.5){
       //自然
      lip_color = red_ziran_lip;
      face_color = red_ziran_face;
      face_alpha = mask_color2.g;
    } else if(red_type < 4.5){
      //日系
      lip_color = red_rixi_lip;
      face_color = red_rixi_face;
      face_alpha = mask_color.a;
    } else {
      //轻熟
      lip_color = red_qinshu_lip;
      face_color = red_qinshu_face;
      face_alpha = mask_color2.r;
    }
    /* int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    int c_mask_ = int(texture2D(tex_faceparsing, texf).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if(c_mask == arrfloat_mask_index[l_brow] || c_mask == arrfloat_mask_index[r_brow]){
      vec3 result_color = vec3(1.0, 0.0, 0.0);
      outcolor = mix(o_color, result_color, red_rate);
    }
    gl_FragColor = vec4(outcolor, origin_color.a);
    return; */
    if (mask_color.r > 0.0) {
      float result_r = blendSoftLight(o_color.r, lip_color.r);
      float result_g = blendSoftLight(o_color.g, lip_color.g);
      float result_b = blendSoftLight(o_color.b, lip_color.b);
      vec3 result_color = vec3(result_r, result_g, result_b);
      outcolor = mix(o_color, result_color, mask_color.r * red_rate * gray_rate_lip);
    }
    if (face_alpha > 0.0) {
      float result_r = blendSoftLight(outcolor.r, face_color.r);
      float result_g = blendSoftLight(outcolor.g, face_color.g);
      float result_b = blendSoftLight(outcolor.b, face_color.b);
      vec3 result_color = vec3(result_r, result_g, result_b);
      outcolor = mix(outcolor, result_color, face_alpha * red_rate * gray_rate_face);
    }
  }
  gl_FragColor = vec4(outcolor, origin_color.a);
}