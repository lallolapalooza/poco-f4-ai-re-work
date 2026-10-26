varying vec2 texc;
varying vec2 texf;
precision highp float;
float exponentialIn(float t) {
  return t == 0.0 ? t : pow(2.0, 30.0 * (t - 1.0));
}
float exponentialOut(float t) {
  return t == 1.0 ? t : 1.0 - pow(2.0, -30.0 * t);
}
float curve1(float x) {
  float a = 20.0;
  float b = -10.0 * abs(x);
  float c = 0.0;
  return pow(a, b) - c;
}
float curve_hue(float x) {
  float a = 5.0;
  float b = -10.0 * abs(x);
  float c = 0.0;
  return pow(a, b);
}
float curve_red(float x) {
  float a = 5.0;
  float k = pow(x * 15.0, 5.0);
  float b = -10.0 * abs(k);
  float c = 0.0;
  return pow(a, b);
}
float curve_blue(float x) {
  float a = 5.0;
  float k = pow(x * 5.0, 5.0);
  float b = -5.0 * abs(k);
  float c = 0.0;
  return pow(a, b);
}
float curve_hue_green(float x) {
  float a = 10.0;
  float b = -50.0 * abs(pow(x, 2.0));
  float c = 0.0;
  return pow(a, b);
}
float curve_saturation1(float x) {
  float a = 5.0;
  float b = -10.0 * abs(x);
  float c = 0.0;
  return pow(a, b) - c;
}
float curve_saturation2(float x) {
  float a = 100.0;
  float b = -150.0 * abs(pow(x, 2.0));
  float c = 0.0;
  return pow(a, b);
}
float curve_saturation_green(float x) {
  return curve_blue(x);
  float a = 5.0;
  float b = -50.0 * abs(pow(x, 2.0));
  float c = 0.0;
  return pow(a, b);
}
float curve_saturation_blue_purple(float x) {
  float a = 15.0;
  float b = -50.0 * abs(pow(x, 2.0));
  float c = 0.0;
  return pow(a, b);
}
float curve_lightness(float x) {
  float a = 15.0;
  float b = -10.0 * abs(x);
  float c = 0.0;
  return pow(a, b) - c;
}
bool is_black_white_gray(vec3 color) {
  const float eps = 0.01;
  const float threshold = 0.1;
  if (abs(color.r - color.g) <= eps && abs(color.g - color.b) <= eps) {
    return true;
  }
  return false;
}
vec3 saturate3f(vec3 v) {
  return vec3(clamp(v.x, 0.0, 1.0), clamp(v.y, 0.0, 1.0), clamp(v.z, 0.0, 1.0));
}
vec3 hue2rgb(float hue) {
  hue = fract(hue);
  vec3 h = saturate3f(vec3(abs(hue * 6.0 - 3.0) - 1.0, 2. - abs(hue * 6. - 2.),
                           2. - abs(hue * 6. - 4.)));
  return h;
}
vec3 rgb2hsl(vec3 c) {
  float cMin = min(min(c.r, c.g), c.b);
  float cMax = max(max(c.r, c.g), c.b);
  float delta = cMax - cMin;
  vec3 hsl = vec3(0., 0., (cMax + cMin) / 2.);
  if (delta != 0.0) {
    if (hsl.z < .5) {
      hsl.y = delta / (cMax + cMin);
    } else {
      hsl.y = delta / (2. - cMax - cMin);
    }
    float deltaR = (((cMax - c.r) / 6.) + (delta / 2.)) / delta;
    float deltaG = (((cMax - c.g) / 6.) + (delta / 2.)) / delta;
    float deltaB = (((cMax - c.b) / 6.) + (delta / 2.)) / delta;
    if (c.r == cMax) {
      hsl.x = deltaB - deltaG;
    } else if (c.g == cMax) {
      hsl.x = (1. / 3.) + deltaR - deltaB;
    } else {
      hsl.x = (2. / 3.) + deltaG - deltaR;
    }
    hsl.x = fract(hsl.x);
  }
  return hsl;
}
vec3 hsl2rgb(vec3 hsl) {
  if (hsl.y == 0.) {
    return vec3(hsl.z);
  } else {
    float b;
    if (hsl.z < .5) {
      b = hsl.z * (1. + hsl.y);
    } else {
      b = hsl.z + hsl.y - hsl.y * hsl.z;
    }
    float a = 2. * hsl.z - b;
    return a + hue2rgb(hsl.x) * (b - a);
  }
}
float distance_hue(float h0, float h1) {
  float diff_hue0 = abs(h0 - h1);
  float diff_hue1 = abs(h0 - h1 + 1.0);
  float diff_hue2 = abs(h0 - h1 - 1.0);
  float diff = min(diff_hue0, diff_hue1);
  diff = min(diff, diff_hue2);
  return diff;
}

vec3 color_tune(vec3 src_color) {
  vec3 result = src_color;
  vec3 src_hsl = rgb2hsl(src_color);
  vec3 result_hsl = src_hsl;
  float hue_src = src_hsl.r;
  float luma = 0.299 * src_color.r + 0.587 * src_color.g + 0.114 * src_color.b;
  float hue_intensity[6];
  float saturation_intensity[6];
  float lightness_intensity[6];
  hue_intensity[0] = red_hue;
  hue_intensity[1] = orange_hue;
  hue_intensity[2] = yellow_hue;
  hue_intensity[3] = green_hue;
  hue_intensity[4] = blue_hue;
  hue_intensity[5] = purple_hue;
  saturation_intensity[0] = red_saturation;
  saturation_intensity[1] = orange_saturation;
  saturation_intensity[2] = yellow_saturation;
  saturation_intensity[3] = green_saturation;
  saturation_intensity[4] = blue_saturation;
  saturation_intensity[5] = purple_saturation;
  lightness_intensity[0] = red_lightness;
  lightness_intensity[1] = orange_lightness;
  lightness_intensity[2] = yellow_lightness;
  lightness_intensity[3] = green_lightness;
  lightness_intensity[4] = blue_lightness;
  lightness_intensity[5] = purple_lightness;
  vec3 color_range[7];
  color_range[0] = vec3(350.0 / 360.0, 335.0 / 360.0, 5.0 / 360.0);
  color_range[1] = vec3(20.0 / 360.0, 10.0 / 360.0, 30.0 / 360.0);
  color_range[2] = vec3(52.0 / 360.0, 32.0 / 360.0, 72.0 / 360.0);
  color_range[3] = vec3(124.0 / 360.0, 72.0 / 360.0, 181.0 / 360.0);
  color_range[4] = vec3(210.0 / 360.0, 189.0 / 360.0, 230.0 / 360.0);
  color_range[5] = vec3(287.0 / 360.0, 267.0 / 360.0, 307.0 / 360.0);
  color_range[6] = vec3(333.0 / 360.0, 318.0 / 360.0, 348.0 / 360.0);
  float hue_target = 0.0;
  float hue_lower = 0.0;
  float hue_upper = 0.0;
  float _hue_intensity = 0.0;
  float _saturation_intensity = 0.0;
  float _lightness_intensity = 0.0;
  for (int i = 0; i < 7; ++i) {
    if (i == 6) {
      _hue_intensity = hue_intensity[0];
      _saturation_intensity = saturation_intensity[0];
      _lightness_intensity = lightness_intensity[0];
    } else {
      _hue_intensity = hue_intensity[i];
      _saturation_intensity = saturation_intensity[i];
      _lightness_intensity = lightness_intensity[i];
    }
    hue_target = color_range[i].x;
    hue_lower = color_range[i].y;
    hue_upper = color_range[i].z;
    float rate= 1.0;
    if(i == 1){
      rate = 0.9;
    }else if(i == 2){
      rate = 0.7;
    }
    float diff_hue = distance_hue(hue_src, hue_target) * rate;
    float t0 = curve_hue(diff_hue);
    if (i == 0 || i == 6) {
      t0 = curve_red(diff_hue);
    }
    if (i == 3) {
      t0 = curve_hue_green(diff_hue);
    }
    if (i == 4) {
      t0 = curve_blue(diff_hue);
    }
    float intensity_hue = clamp(_hue_intensity, -1.0, 1.0);
    float h = intensity_hue > 0.0 ? distance_hue(hue_upper, hue_target)
                                  : distance_hue(hue_target, hue_lower);
    result_hsl.r += h * intensity_hue * t0;
    float t1 = 0.0;
    t1 = curve_saturation2(diff_hue);
    if (i == 0 || i == 6) {
      t1 = curve_red(diff_hue);
    }
    if (i == 3) {
      t1 = curve_saturation_green(diff_hue);
    }
    if (i == 4) {
      t1 = curve_blue(diff_hue);
    }
    if (i == 5) {
      t1 = curve_saturation_blue_purple(diff_hue);
    }
    float intensity_saturation = clamp(_saturation_intensity, -1.0, 1.0);
    float s = intensity_saturation > 0.0 ? 0.4 : 1.0;
    result_hsl.g *= (intensity_saturation * t1 * s + 1.0);
    result_hsl.g = clamp(result_hsl.g, 0.0, 1.0);
    float t2 = curve_saturation2(diff_hue);
    if (i == 0) {
      t2 *= _lightness_intensity > 0.0 ? 1.0 : 1.5;
    }
    if (i == 3) {
      t2 = curve_saturation_green(diff_hue);
    }
    if (i == 4) {
      t2 = curve_saturation_blue_purple(diff_hue);
    }
    if (i == 5) {
      t2 = curve_saturation_blue_purple(diff_hue);
    }
    float intensity_lightness = clamp(_lightness_intensity, -1.0, 1.0);
    result_hsl.b += src_hsl.y * src_hsl.z * intensity_lightness * t2 * 0.25;
    result_hsl.b = clamp(result_hsl.b, 0.0, 1.0);
  }
  result = hsl2rgb(result_hsl);
  result = is_black_white_gray(src_color.rgb) ? src_color.rgb : result;
  return result;
}

vec2 change_texc(vec2 st, int type) {
  if(type == 0){
    return vec2(st.x * 0.5, st.y);
  }else{
    return vec2(0.5 + st.x * 0.5, st.y);
  }
}

vec4 shader_main_s_color_custom_shader_whitten (vec4 C,sampler2D tex_lut,int type){
	float color_scale_s_color_custom_shader_whitten = 1.0;
	float raw_s_color_custom_shader_whitten = 8.0;
	float col_s_color_custom_shader_whitten = 8.0;
	float dim_s_color_custom_shader_whitten = 64.0;
	vec4 textureColor = C;
	textureColor *= color_scale_s_color_custom_shader_whitten  ;
	float blueColor = textureColor.b * (dim_s_color_custom_shader_whitten   - 1.0);
	float r_raw = 1.0 / raw_s_color_custom_shader_whitten  ;
	float r_col = 1.0 / col_s_color_custom_shader_whitten  ;
	vec2 quad1;
	quad1.y = floor(floor(blueColor) / raw_s_color_custom_shader_whitten  );
	quad1.x = floor(blueColor) - (quad1.y * col_s_color_custom_shader_whitten  );
	vec2 quad2; 
	quad2.y = floor(ceil(blueColor) / raw_s_color_custom_shader_whitten  );
	quad2.x = ceil(blueColor) - (quad2.y * col_s_color_custom_shader_whitten  );
	vec2 texPos1;      
	texPos1.x = (quad1.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos1.y = (quad1.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec2 texPos2;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	texPos2.x = (quad2.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos2.y = (quad2.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec4 newColor1 = texture2D(tex_lut  , change_texc(texPos1, type));
	vec4 newColor2 = texture2D(tex_lut  , change_texc(texPos2, type));
	vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
	return vec4(newColor1.rgb,C.a);
}

// vec4 do_hsl(vec4 incolor) {
//   vec4 result_color = incolor;
//   vec4 blue_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_blue_hue, blue_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,blue_hue_color,abs(blue_hue));
//   vec4 blue_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_blue_lightness, blue_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,blue_lightness_color,abs(blue_lightness));
//   vec4 blue_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_blue_saturation, blue_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,blue_saturation_color,abs(blue_saturation));
//   vec4 green_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_green_hue, green_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,green_hue_color,abs(green_hue));
//   vec4 green_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_green_lightness, green_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,green_lightness_color,abs(green_lightness));
//   vec4 green_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_green_saturation, green_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,green_saturation_color,abs(green_saturation));
//   vec4 orange_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_orange_hue, orange_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,orange_hue_color,abs(orange_hue));
//   vec4 orange_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_orange_lightness, orange_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,orange_lightness_color,abs(orange_lightness));
//   vec4 orange_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_orange_saturation, orange_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,orange_saturation_color,abs(orange_saturation));
//   vec4 purple_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_purple_hue, purple_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,purple_hue_color,abs(purple_hue));
//   vec4 purple_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_purple_lightness, purple_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,purple_lightness_color,abs(purple_lightness));
//   vec4 purple_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_purple_saturation, purple_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,purple_saturation_color,abs(purple_saturation));
//   vec4 red_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_red_hue, red_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,red_hue_color,abs(red_hue));
//   vec4 red_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_red_lightness, red_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,red_lightness_color,abs(red_lightness));
//   vec4 red_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_red_saturation, red_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,red_saturation_color,abs(red_saturation));
//   vec4 yellow_hue_color = shader_main_s_color_custom_shader_whitten(result_color, tex_yellow_hue, yellow_hue > 0.0 ? 0 : 1);
//   result_color = mix(result_color,yellow_hue_color,abs(yellow_hue));
//   vec4 yellow_lightness_color = shader_main_s_color_custom_shader_whitten(result_color, tex_yellow_lightness, yellow_lightness > 0.0 ? 0 : 1);
//   result_color = mix(result_color,yellow_lightness_color,abs(yellow_lightness));
//   vec4 yellow_saturation_color = shader_main_s_color_custom_shader_whitten(result_color, tex_yellow_saturation, yellow_saturation > 0.0 ? 0 : 1);
//   result_color = mix(result_color,yellow_saturation_color,abs(yellow_saturation));
//   return result_color;
// }

vec3 hsl_calculate(vec3 diff_hsl, float h, float hue, float saturation, float brightness, vec4 color_range) {
  if (color_range.x < color_range.y && color_range.y > color_range.z && color_range.z < color_range.w) {
    if ((h >= color_range.y && h <= 1.0) || (h >= 0.0 && h <= color_range.z)) {
        diff_hsl.x += hue;
        diff_hsl.y += saturation;
        diff_hsl.z += brightness;
        return diff_hsl;
    }
    if (h >= color_range.x && h <= color_range.y) {
        diff_hsl.x += hue * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.y += saturation * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.z += brightness * (h - color_range.x) / (color_range.y - color_range.x);
        return diff_hsl;
    }
    if (h >= color_range.z && h <= color_range.w) {
        diff_hsl.x += hue * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.y += saturation * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.z += brightness * (color_range.w - h) / (color_range.w - color_range.z);
        return diff_hsl;
    }
  }
  if (color_range.x > color_range.y && color_range.y < color_range.z && color_range.z < color_range.w) {
    if (h >= color_range.y && h <= color_range.z) {
        diff_hsl.x += hue;
        diff_hsl.y += saturation;
        diff_hsl.z += brightness;
        return diff_hsl;
    }
    if (h >= 0.0 && h <= color_range.y) {
        diff_hsl.x += hue * (h + 1.0 - color_range.x) / (color_range.y + 1.0 - color_range.x);
        diff_hsl.y += saturation * (h + 1.0 - color_range.x) / (color_range.y + 1.0 - color_range.x);
        diff_hsl.z += brightness * (h + 1.0 - color_range.x) / (color_range.y + 1.0 - color_range.x);
        return diff_hsl;
    }
    if (h >= color_range.x && h <= 1.0) {
        diff_hsl.x += hue * (h - color_range.x) / (color_range.y + 1.0 - color_range.x);
        diff_hsl.y += saturation * (h - color_range.x) / (color_range.y + 1.0 - color_range.x);
        diff_hsl.z += brightness * (h - color_range.x) / (color_range.y + 1.0 - color_range.x);
        return diff_hsl;
    }
    if (h >= color_range.z && h <= color_range.w) {
        diff_hsl.x += hue * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.y += saturation * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.z += brightness * (color_range.w - h) / (color_range.w - color_range.z);
        return diff_hsl;
    }
  }
  if (color_range.x <= color_range.y && color_range.y < color_range.z && color_range.z <= color_range.w) {
    if (h >= color_range.y && h <= color_range.z) {
        diff_hsl.x += hue;
        diff_hsl.y += saturation;
        diff_hsl.z += brightness;
        return diff_hsl;
    }
    if (h >= color_range.x && h <= color_range.y) {
        diff_hsl.x += hue * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.y += saturation * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.z += brightness * (h - color_range.x) / (color_range.y - color_range.x);
        return diff_hsl;
    }
    if (h >= color_range.z && h <= color_range.w) {
        diff_hsl.x += hue * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.y += saturation * (color_range.w - h) / (color_range.w - color_range.z);
        diff_hsl.z += brightness * (color_range.w - h) / (color_range.w - color_range.z);
        return diff_hsl;
    }
  }
  if (color_range.x < color_range.y && color_range.y < color_range.z && color_range.z > color_range.w) {
    if (h >= color_range.y && h <= color_range.z) {
        diff_hsl.x += hue;
        diff_hsl.y += saturation;
        diff_hsl.z += brightness;
        return diff_hsl;
    }
    if (h >= color_range.x && h <= color_range.y) {
        diff_hsl.x += hue * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.y += saturation * (h - color_range.x) / (color_range.y - color_range.x);
        diff_hsl.z += brightness * (h - color_range.x) / (color_range.y - color_range.x);
        return diff_hsl;
    }
    if (h >= color_range.z && h <= 1.0) {
        diff_hsl.x += hue * (color_range.w + 1.0 - h) / (color_range.w + 1.0 - color_range.z);
        diff_hsl.y += saturation * (color_range.w + 1.0 - h) / (color_range.w + 1.0 - color_range.z);
        diff_hsl.z += brightness * (color_range.w + 1.0 - h) / (color_range.w + 1.0 - color_range.z);
        return diff_hsl;
    }
    if (h >= 0.0 && h <= color_range.w) {
        diff_hsl.x += hue * (color_range.w - h) / (color_range.w + 1.0 - color_range.z);
        diff_hsl.y += saturation * (color_range.w - h) / (color_range.w + 1.0 - color_range.z);
        diff_hsl.z += brightness * (color_range.w - h) / (color_range.w + 1.0 - color_range.z);
        return diff_hsl;
    }
  }
  return diff_hsl;
}

vec3 do_hsl(vec3 src_color) {
    vec3 src_hsl = rgb2hsl(src_color);
    vec3 diff_hsl = vec3(0.0);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, red_hue, red_saturation, red_lightness, arrfloat4_color_range[0]);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, orange_hue, orange_saturation, orange_lightness, arrfloat4_color_range[1]);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, yellow_hue, yellow_saturation, yellow_lightness, arrfloat4_color_range[2]);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, green_hue, green_saturation, green_lightness, arrfloat4_color_range[3]);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, blue_hue, blue_saturation, blue_lightness, arrfloat4_color_range[4]);
    diff_hsl = hsl_calculate(diff_hsl, src_hsl.x, purple_hue, purple_saturation, purple_lightness, arrfloat4_color_range[5]);
    
    src_hsl.x += diff_hsl.x;
    src_hsl.x -= floor(src_hsl.x);
 
    diff_hsl.y = clamp(diff_hsl.y, -1.0, 1.0);
    if (diff_hsl.y < 0.0) {
        src_hsl.y *= (1.0 + diff_hsl.y);
    } else {
        src_hsl.y += (src_hsl.y * diff_hsl.y);
    }
  
    diff_hsl.z = clamp(diff_hsl.z, -1.0, 1.0);
    float radio = src_hsl.y;
    if (src_hsl.z >= 0.5) {
        radio = src_hsl.y;
    } else {
        radio = src_hsl.y * src_hsl.z * 2.0;
    }
    src_hsl.z = src_hsl.z + radio * (1.0 - src_hsl.z) * diff_hsl.z;
    if (diff_hsl.z < 0.0) {
        src_hsl.y = src_hsl.y * (1.0 + diff_hsl.z);
    } else {
        src_hsl.y = src_hsl.y * (1.0 - diff_hsl.z);
    }
    src_hsl = clamp(src_hsl, 0.0, 1.0);
    return hsl2rgb(src_hsl);
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
	//incolor.rgb = color_tune(incolor.rgb);
  //incolor = do_hsl(incolor);
  incolor.rgb = do_hsl(incolor.rgb);
	gl_FragColor = incolor;
}
