varying vec2 texc;

vec4 lut8x8(sampler2D lut, vec4 C) {
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
  vec4 newColor1 = texture2D(lut, texPos1);
  vec4 newColor2 = texture2D(lut, texPos2);

  vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
  return newColor;
}

void main() {
  vec2 texv = texc;

  vec4 origin_color = texture2D(tex_origin, texv);
  float skinwhite_alpha = texture2D(tex_lut, texv).r;
  if (skinwhite_alpha <= 0.3) skinwhite_alpha = 0.3;

  vec4 outcolor = shader_main_s_color_custom_shader_whitten(origin_color);
  outcolor =
      outcolor * skinwhite_alpha + origin_color * (1.0 - skinwhite_alpha);
  gl_FragColor = outcolor;
}