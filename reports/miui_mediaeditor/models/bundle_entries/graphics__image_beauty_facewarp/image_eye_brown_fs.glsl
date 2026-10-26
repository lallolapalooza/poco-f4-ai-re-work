
varying vec2 texc;
varying vec2 texf;

float blendSoftLight(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}

float blendStrongLight(float base, float blend) { 
  float color1 = 2.0 * base * blend; 
  float color2 = 1.0 - (1.0 - base) * (1.0 - blend) * 2.0; 
  return mix(color1, color2, step(0.5, blend)); 
} 

vec3 saturation_change(vec3 source, float saturation) {
    vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);
    float luminance = dot(source.rgb, luminanceWeighting);
    vec3 greyScaleColor = vec3(luminance);
    return mix(greyScaleColor, source.rgb, saturation);
}

vec3 brightness_contrast_change(vec3 source,float brightness,float contrast){
  float PI = 3.1415926;
	float B = brightness / 255.0;
	float c = contrast / 255.0;
	float k = tan((45.0 + 44.0 * c) / 180.0 * PI);
	vec3 colorrbg = ((source*255.0 - 127.5 * (1.0 - B)) * k + 127.5 * (1.0 + B)) / 255.0;
    return colorrbg;
}

float blendLight(float base, float blend){
  if(sharpenbrown_type < 0.5){
      //默认
      return blendStrongLight(base,blend);
    } else if(sharpenbrown_type < 1.5){
      //经典
      return blendSoftLight(base,blend);
    } else if(sharpenbrown_type < 2.5){
      //质感
      return blendStrongLight(base,blend);
    } else if(sharpenbrown_type < 3.5){
       //自然
      return blendStrongLight(base,blend);
    } else if(sharpenbrown_type < 4.5){
      //日系
      return blendStrongLight(base,blend);
    } else {
      //轻熟
      return blendStrongLight(base,blend);
    }
}

vec3 eye_curve(vec3 color,float rate)
{
    float new_o_r = texture2D(tex_eye_curve_mask,vec2(color.r,0.0)).r;
    float new_o_g = texture2D(tex_eye_curve_mask,vec2(color.g,0.0)).r;
    float new_o_b = texture2D(tex_eye_curve_mask,vec2(color.b,0.0)).r;
    vec3 new_color = vec3(new_o_r,new_o_g,new_o_b);
    new_color = clamp(new_color,0.0,1.0);
    return color + (new_color - color) * rate;
} 

vec3 lighteye(vec3 incolor){
  vec3 outcolor = incolor.rgb;

  vec2 ls_texv = texf - offset;
  ls_texv.x /= wh.x;
  ls_texv.y /= wh.y;

  if (texf.x >= offset_eye.x && texf.x <= offset_eye.x + wh_eye.x && texf.y >= offset_eye.y &&
      texf.y <= offset_eye.y + wh_eye.y) {
    vec2 eye_texv = texf - offset_eye; 
    eye_texv.x /= wh_eye.x;
    eye_texv.y /= wh_eye.y;

    vec4 color_lighteye = texture2D(tex_lighteye, eye_texv);
    vec3 color_lighteye_detail;
    if(is_preview_of_getresult < 0.5){
      color_lighteye_detail = texture2D(tex_lighteye_detail, eye_texv).rgb;
    }
    else{
      color_lighteye_detail = texture2D(tex_lighteye_detail, texc).rgb;
    }

    vec3 source_eyeblack = incolor.rgb;
    vec3 result_eyeblack = source_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack = mix(source_eyeblack, result_eyeblack, 0.65);
    vec3 result_eyeblack1 = result_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack1 = mix(result_eyeblack, result_eyeblack1, 0.325);
    //vec3 result_eyeblack1 = result_eyeblack;
    result_eyeblack = saturation_change(result_eyeblack1,1.15);

    vec3 source_eyewhite = color_lighteye.rgb;
    vec3 result_eyewhite = source_eyewhite + (color_lighteye_detail * 2.0 - 1.0); 
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.65);

    if (source_eyewhite.r > source_eyewhite.g) {
        source_eyewhite.g += 0.8*(source_eyewhite.r - source_eyewhite.g);
    }
    if (source_eyewhite.r > source_eyewhite.b) {
        source_eyewhite.b += 0.8*(source_eyewhite.r - source_eyewhite.b);
    }
    source_eyewhite = clamp(source_eyewhite,0.0,1.0);
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.3);
    result_eyewhite = eye_curve(result_eyewhite,0.65);

    vec2 pos_eye = ls_texv * pic_wh;
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    float dis_rate = 0.0;
    if (lefteye_dis <= righteye_dis) {
        dis_rate = lefteye_dis / left_iris_radius;
    } else {
        dis_rate = righteye_dis / right_iris_radius;
    }

    float eyeball_alpha = smoothstep(1.6, 0.8, dis_rate);
    vec3 origin_color = incolor.rgb;
    vec3 out_circle_color = mix(origin_color,result_eyewhite,1.0 - eyeball_alpha);
    dis_rate = smoothstep(1.1, 0.9, dis_rate);
    vec3 in_circle_color = result_eyeblack;
    vec3 result_color = in_circle_color * dis_rate + out_circle_color * (1.0 - dis_rate);

    float lighteyerate = lighteye_rate * color_lighteye.a;
    outcolor = mix(outcolor, result_color, lighteyerate);
  }
  return outcolor;
}

vec3 sharpenbrown(vec3 incolor){
  vec3 outcolor = incolor.rgb;

  if (texf.x >= offset_brown.x && texf.x <= offset_brown.x + wh_brown.x && texf.y >= offset_brown.y &&
      texf.y <= offset_brown.y + wh_brown.y) {
    vec2 brown_texv = texf - offset_brown; 
    brown_texv.x /= wh_brown.x;
    brown_texv.y /= wh_brown.y;

    vec3 grat_temp = vec3(0.3,0.59,0.11);
    float gray = dot(outcolor,grat_temp);
    float gray_rate = 1.0;
    gray_rate = texture2D(tex_brown_curve_mask,vec2(gray,0.0)).r;
    gray_rate = clamp(gray_rate,0.0,1.0);
    //gray_rate = 1.0;
  
    vec4 color_sharpenbrown = texture2D(tex_sharpenbrown, brown_texv);
    float sharpenbrownrate = sharpenbrown_rate * color_sharpenbrown.a;
    outcolor = mix(outcolor, color_sharpenbrown.rgb, sharpenbrownrate);
    if(is_preview_of_getresult < 0.5){
      outcolor = mix(outcolor, color_sharpenbrown.rgb, sharpenbrownrate);
    }
    else{
      vec3 color_sharpenbrown_result_color = texture2D(tex_sharpenbrown_detail, texc).rgb;
      outcolor = mix(outcolor, color_sharpenbrown_result_color, sharpenbrownrate);
    }

    //vec4 color_sharpenbrown_mask = texture2D(tex_sharpenbrown_mask, ls_texv);
    vec3 brown_color = vec3(0.0);
    float rate = 1.0;
    if(sharpenbrown_type < 0.5){
      //默认
      brown_color = red_normal_brown;
      gray_rate = 1.0;
    } else if(sharpenbrown_type < 1.5){
      //经典
      brown_color = red_jingdian_brown;
      rate = 1.0;
    } else if(sharpenbrown_type < 2.5){
      //质感
      brown_color = red_zhigan_brown;
      rate = 0.8;
    } else if(sharpenbrown_type < 3.5){
       //自然
      brown_color = red_ziran_brown;
      rate = 0.5;
    } else if(sharpenbrown_type < 4.5){
      //日系
      brown_color = red_rixi_brown;
    } else {
      //轻熟
      brown_color = red_qinshu_brown;
    }
    //float sharpenbrownrate_alpha = sharpenbrownrate * color_sharpenbrown_mask.a;
    if(sharpenbrownrate > 0.0){
      float result_r = blendLight(outcolor.r, brown_color.r);
      float result_g = blendLight(outcolor.g, brown_color.g);
      float result_b = blendLight(outcolor.b, brown_color.b);
      vec3 result_color = vec3(result_r, result_g, result_b);
      outcolor = mix(outcolor, result_color, sharpenbrownrate * rate * gray_rate);
    } 
  }
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;
  if(lighteye_rate > 0.001){
    outcolor = lighteye(outcolor);
  }
  if(sharpenbrown_rate > 0.001){
    outcolor = sharpenbrown(outcolor);
  }
  gl_FragColor = vec4(outcolor, origin_color.a);
}