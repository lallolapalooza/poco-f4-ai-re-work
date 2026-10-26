varying vec2 texc;
varying vec2 texf;
// precision highp float;
// precision highp int;

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

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec4 alpha = texture2D(tex_skinwhite, texf);
  vec4 outcolor = origin_color;
  if (skinwhite_rate >= 0.5) {
    float skinwhite_alpha = clamp(alpha.g + alpha.b,0.0,1.0);
    if (skinwhite_alpha <= 0.3) skinwhite_alpha = 0.3;
    vec4 white_outcolor = shader_main_s_color_custom_shader_whitten(outcolor, tex_skinwhite_lut);
    outcolor = mix(outcolor, white_outcolor, skinwhite_alpha * (skinwhite_rate - 0.5) / 0.5);
  } else {
    float skinblack_alpha = clamp(alpha.g + alpha.b,0.0,1.0);
    if (skinblack_alpha <= 0.1) skinblack_alpha = 0.1;
    vec4 black_outcolor = shader_main_s_color_custom_shader_whitten(outcolor, tex_skinblack_lut);
    outcolor = mix(outcolor, black_outcolor, skinblack_alpha * (0.5 - skinwhite_rate) / 0.5);
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
  gl_FragColor = vec4(outcolor.rgb,origin_color.a);
}