varying vec2 texc;
varying vec2 texf;


vec4 shader_main_s_color_custom_shader_whitten(vec4 C, sampler2D tex_whiteblack_lut) {
  float raw_s_color_custom_shader_whitten = 8.0;
  float col_s_color_custom_shader_whitten = 8.0;
  float dim_s_color_custom_shader_whitten = 64.0;
  float color_scale_s_color_custom_shader_whitten = 1.0;

  vec4 textureColor = C;
  textureColor *= color_scale_s_color_custom_shader_whitten;
  float blueColor = textureColor.b * (dim_s_color_custom_shader_whitten - 1.0);
  float r_raw = 1.0 / raw_s_color_custom_shader_whitten;
  float r_col = 1.0 / col_s_color_custom_shader_whitten;

  vec2 quad1;
  quad1.y = floor(floor(blueColor) / raw_s_color_custom_shader_whitten);
  quad1.x = floor(blueColor) - (quad1.y * col_s_color_custom_shader_whitten);

  vec2 quad2;
  quad2.y = floor(ceil(blueColor) / raw_s_color_custom_shader_whitten);
  quad2.x = ceil(blueColor) - (quad2.y * col_s_color_custom_shader_whitten);

  vec2 texPos1;
  texPos1.x = (quad1.x * r_col) +
              0.5 / (col_s_color_custom_shader_whitten *
                     dim_s_color_custom_shader_whitten) +
              ((r_col - 1.0 / (col_s_color_custom_shader_whitten *
                               dim_s_color_custom_shader_whitten)) *
               textureColor.r);
  texPos1.y = (quad1.y * r_raw) +
              0.5 / (raw_s_color_custom_shader_whitten *
                     dim_s_color_custom_shader_whitten) +
              ((r_raw - 1.0 / (raw_s_color_custom_shader_whitten *
                               dim_s_color_custom_shader_whitten)) *
               textureColor.g);

  vec2 texPos2;
  texPos2.x = (quad2.x * r_col) +
              0.5 / (col_s_color_custom_shader_whitten *
                     dim_s_color_custom_shader_whitten) +
              ((r_col - 1.0 / (col_s_color_custom_shader_whitten *
                               dim_s_color_custom_shader_whitten)) *
               textureColor.r);
  texPos2.y = (quad2.y * r_raw) +
              0.5 / (raw_s_color_custom_shader_whitten *
                     dim_s_color_custom_shader_whitten) +
              ((r_raw - 1.0 / (raw_s_color_custom_shader_whitten *
                               dim_s_color_custom_shader_whitten)) *
               textureColor.g);
  vec4 newColor1 = texture2D(tex_whiteblack_lut, texPos1);
  vec4 newColor2 = texture2D(tex_whiteblack_lut, texPos2);

  vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
  return newColor;
}

vec4 lut4x4(vec4 textureColor, sampler2D tex_zhongxingbai_lut) {
  float blueColor = textureColor.b * 15.0;
  vec2 quad1;
  quad1.y = max(min(4.0, floor(floor(blueColor) / 4.0)), 0.0);
  quad1.x = max(min(4.0, floor(blueColor) - (quad1.y * 4.0)), 0.0);
  vec2 quad2;
  quad2.y = max(min(floor(ceil(blueColor) / 4.0), 4.0), 0.0);
  quad2.x = max(min(ceil(blueColor) - (quad2.y * 4.0), 4.0), 0.0);
  vec2 texPos1;
  texPos1.x =
      (quad1.x * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.r);
  texPos1.y =
      (quad1.y * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.g);
  vec2 texPos2;
  texPos2.x =
      (quad2.x * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.r);
  texPos2.y =
      (quad2.y * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.g);
  vec4 newColor1 = texture2D(tex_zhongxingbai_lut, texPos1);
  vec4 newColor2 = texture2D(tex_zhongxingbai_lut, texPos2);
  vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
  return newColor;
}

float blendSoftLight(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}

float blendSuperposition(float base, float blend) {
    float color1 = base * blend * 2.0;
    float color2 = 1.0 - (1.0 - base)*(1.0 - blend) * 2.0;
    return mix(color1, color2, step(0.5, base));
}

vec4 skin_white(vec4 incolor){
  vec4 alpha = texture2D(tex_skinwhite, texf);
  vec4 outcolor = incolor;
  float face_rate_color = texture2D(tex_face_rate,texf).g;
  if (skinwhite_rate >= 0.5) {
    //float skinwhite_alpha = alpha.r;
    float skinwhite_alpha = clamp(alpha.g + alpha.b,0.0,1.0);
    if (skinwhite_alpha <= 0.3) skinwhite_alpha = 0.3;
    vec4 white_outcolor = shader_main_s_color_custom_shader_whitten(outcolor, tex_skinwhite_lut);
    outcolor = mix(outcolor, white_outcolor, skinwhite_alpha * (skinwhite_rate - 0.5) / 0.5);
  } else {
    float skinblack_alpha = clamp(alpha.g + alpha.b,0.0,1.0);
    if (skinblack_alpha <= 0.1) skinblack_alpha = 0.1;
    vec4 black_outcolor = shader_main_s_color_custom_shader_whitten(outcolor, tex_skinblack_lut);
    outcolor = mix(outcolor, black_outcolor, skinblack_alpha * (0.5 - skinwhite_rate) / 0.5);
    //face_rate_color = smoothstep(0.0,0.2,face_rate_color);
    //outcolor = mix(outcolor,vec4(1.0,0.0,0.0,1.0),face_rate_color * (0.5 - skinwhite_rate) / 0.5);
  }

  float warmcold_alpha = alpha.r;
  if (warmcold_alpha <= 0.1) warmcold_alpha = 0.1;
  if (warm_cold_rate >= 0.5) {
    vec4 warm_outcolor = lut4x4(outcolor, tex_zhongxingbai_warm);
    outcolor = mix(outcolor, warm_outcolor, warmcold_alpha * (warm_cold_rate - 0.5) / 0.5);
  } else {
    vec4 cold_outcolor = lut4x4(outcolor, tex_zhongxingbai_cold);
    outcolor = mix(outcolor, cold_outcolor, warmcold_alpha * (0.5 - warm_cold_rate) / 0.5);
  }
  return outcolor;
}

vec3 skin_red(vec3 incolor){
  vec3 outcolor = incolor.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 red_texv = texf - offset;
    red_texv.x /= wh.x;
    red_texv.y /= wh.y;

    float gray_rate_lip = 1.0;
    float gray_rate_face = 1.0;
    vec3 grat_temp = vec3(0.3,0.59,0.11);
    float gray = dot(outcolor,grat_temp);

    vec3 o_color = incolor.rgb;
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
    } else if(red_type < 5.5){
      //轻熟
      lip_color = red_qinshu_lip;
      face_color = red_qinshu_face;
      face_alpha = mask_color2.r;
    } else if(red_type < 6.5){
      //淡颜系
      lip_color = red_light_lip;
      face_color = red_light_face;
      face_alpha = mask_color2.r;
    } else {
      //浓颜系
      lip_color = red_strong_lip;
      face_color = red_strong_face;
      face_alpha = mask_color2.r;
    }
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
  return outcolor;
}

vec3 threed_jili(vec3 incolor){
  vec3 o_color = incolor.rgb;
  vec3 grat_temp = vec3(0.3,0.59,0.11);
  float gray = dot(o_color,grat_temp);
  float gray_rate = 0.0;
  gray_rate = texture2D(tex_curve_mask,vec2(gray,0.0)).r;
  float gray_rate_threed = clamp(gray_rate,0.4,1.0);
  float gray_rate_jili = clamp(gray_rate,0.0,1.0);
  // gray_rate = 1.0;
  vec3 outcolor = o_color;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 threed_texv = texf - offset;
    threed_texv.x /= wh.x;
    threed_texv.y /= wh.y;
    vec4 threed_jili_color = texture2D(tex_threedjili_mask, threed_texv);
    float threed_color = 0.5;
    float makeup_mask = 1.0;
    if(threed_type < 0.5){
      threed_color = threed_jili_color.r;
    }else if(threed_type < 1.5){
      threed_color = texture2D(tex_threedmakeup, threed_texv).r;
      makeup_mask = texture2D(tex_makeupmask,threed_texv).r;
    }else if(threed_type < 2.5){
      threed_color = texture2D(tex_threedmakeup, threed_texv).g;
      makeup_mask = texture2D(tex_makeupmask,threed_texv).r;
    }else{
      threed_color = texture2D(tex_threedmakeup, threed_texv).b;
      makeup_mask = texture2D(tex_makeupmask,threed_texv).r;
    }
    float result_r = blendSoftLight(o_color.r, threed_color);
    float result_g = blendSoftLight(o_color.g, threed_color);
    float result_b = blendSoftLight(o_color.b, threed_color);
    vec3 result_color = vec3(result_r, result_g, result_b);
    outcolor = mix(o_color, result_color, threed_rate * gray_rate_threed * smoothstep(0.2, 1.0, makeup_mask));

    float jili_color = threed_jili_color.g;
    float protect_alpha = threed_jili_color.b;
    result_r = blendSoftLight(outcolor.r, jili_color);
    result_g = blendSoftLight(outcolor.g, jili_color);
    result_b = blendSoftLight(outcolor.b, jili_color);
    result_color = vec3(result_r, result_g, result_b);
    float rate = jili_rate * protect_alpha * gray_rate_jili;
    rate = clamp(rate * 2.0, 0.0, 1.0);
    outcolor = mix(outcolor, result_color, rate);

    vec3 jili_highpass_color;
    if(is_preview_of_getresult < 0.5){
      jili_highpass_color = vec3(threed_jili_color.a);
    }else{
      jili_highpass_color = vec3(texture2D(tex_jili_mask_origin,texc).b);
    }
    result_color = outcolor + (jili_highpass_color * 2.0 - 1.0);
    outcolor = mix(outcolor, result_color, jili_rate * protect_alpha * 0.8);
  }
  return outcolor;
}

vec4 teeth_white(vec4 incolor){
  vec4 outcolor = incolor;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 teeth_texv = texf - offset;
    teeth_texv.x /= wh.x;
    teeth_texv.y /= wh.y;
    float alpha = texture2D(tex_teeth_mask, teeth_texv).r;
    vec4 white_outcolor = shader_main_s_color_custom_shader_whitten(outcolor, tex_teethwhite_lut);
    vec4 white_outcolor1 = shader_main_s_color_custom_shader_whitten(white_outcolor, tex_teethwhite_lut);
    white_outcolor = mix(white_outcolor,white_outcolor1,0.2);
    outcolor = mix(outcolor, white_outcolor, alpha * teeth_rate);
    //outcolor = mix(incolor,vec4(1.0,0.0,0.0,1.0),alpha * 0.6);    
  }
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec4 outcolor = origin_color;
  if(abs(skinwhite_rate - 0.5) > 0.001 || abs(warm_cold_rate - 0.5) > 0.001){
    outcolor = skin_white(outcolor);
  }
  if(red_rate > 0.001){
    outcolor.rgb = skin_red(outcolor.rgb);
  }
  if(jili_rate > 0.001 || threed_rate > 0.001){
    outcolor.rgb = threed_jili(outcolor.rgb);
  }
  if(teeth_rate > 0.001){
    outcolor = teeth_white(outcolor);
  }

  gl_FragColor = vec4(outcolor.rgb,origin_color.a);
}