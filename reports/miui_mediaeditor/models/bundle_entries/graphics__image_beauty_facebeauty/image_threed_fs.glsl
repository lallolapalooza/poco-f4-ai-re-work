varying vec2 texc;
varying vec2 texf;

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

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 o_color = origin_color.rgb;
  vec3 grat_temp = vec3(0.3,0.59,0.11);
  float gray = dot(o_color,grat_temp);
  float gray_rate = 0.0;
  gray_rate = texture2D(tex_curve_mask,vec2(gray,0.0)).r;
  gray_rate = clamp(gray_rate,0.0,1.0);
  vec3 outcolor = o_color;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 threed_texv = texf - offset;
    threed_texv.x /= wh.x;
    threed_texv.y /= wh.y;
    vec4 threed_jili_color = texture2D(tex_threedjili_mask, threed_texv);
    float threed_color = threed_jili_color.r;
    float result_r = blendSoftLight(o_color.r, threed_color);
    float result_g = blendSoftLight(o_color.g, threed_color);
    float result_b = blendSoftLight(o_color.b, threed_color);
    vec3 result_color = vec3(result_r, result_g, result_b);
    outcolor = mix(o_color, result_color, threed_rate * gray_rate);

    float jili_color = threed_jili_color.g;
    float protect_alpha = threed_jili_color.b;
    result_r = blendSoftLight(outcolor.r, jili_color);
    result_g = blendSoftLight(outcolor.g, jili_color);
    result_b = blendSoftLight(outcolor.b, jili_color);
    result_color = vec3(result_r, result_g, result_b);
    float rate = jili_rate * protect_alpha * gray_rate;
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
  gl_FragColor = vec4(outcolor, origin_color.a);
}