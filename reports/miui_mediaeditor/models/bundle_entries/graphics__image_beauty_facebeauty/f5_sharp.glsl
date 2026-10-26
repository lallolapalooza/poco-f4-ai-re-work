varying vec4 st_offset1;
varying vec4 st_offset2;
void main() {
  vec4 origin_color = texture2D(tex_src, st);
  vec4 blur_color = texture2D(tex_mean, st);
  vec4 diff_blur_color = texture2D(tex_diff, st);
  //illumination clampresult_color
  float value = clamp((min(origin_color.r, blur_color.r - 0.1) - 0.2) * 4.0, 0.0, 1.0);
  float averageHP = diff_blur_color.r;
    //frequency higher,then intensity lowwer
  float currentIntensity = (1.0 - averageHP / (averageHP + 0.1)) * value;
  if (currentIntensity > 1.0 - frequencyRangeValue.x) {
    currentIntensity *= frequencyRangeBlur.x;
  } else if (currentIntensity > 1.0 - frequencyRangeValue.y) {
    currentIntensity *= frequencyRangeBlur.y;
  } else if (currentIntensity > 1.0 - frequencyRangeValue.z) {
    currentIntensity *= frequencyRangeBlur.z;
  } else {
    currentIntensity *= frequencyRangeBlur.w;
  }
  vec3 resultColor = mix(origin_color.rgb, blur_color.rgb, currentIntensity);
  
  gl_FragColor = vec4(resultColor.rgb, origin_color.a);
}