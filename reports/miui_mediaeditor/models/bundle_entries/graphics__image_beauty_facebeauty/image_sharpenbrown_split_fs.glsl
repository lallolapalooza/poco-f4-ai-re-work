
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

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;

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
  gl_FragColor = vec4(outcolor, origin_color.a);
}