varying vec2 texc;
void main() {
  vec3 wrinkle_diff = texture2D(tex_wrinkle,texc).rgb;
  vec4 wrinkle_blur = texture2D(tex_wrinkle_blur,texc);
  vec3 wrinkle_ori = texture2D(tex_wrinkle_ori,texc).rgb;
  vec3 wrinkle_result_blur = wrinkle_blur.rgb + wrinkle_diff - wrinkle_ori;
  float wrinkle_alpha = 1.0;
  if(texc.x <= 3.0 * offset_w || texc.x >= 1.0 - 3.0 * offset_w 
    || texc.y <= 3.0 * offset_h || texc.y >= 1.0 - 3.0 * offset_h){
    wrinkle_alpha = 0.0;
  }
  gl_FragColor = vec4(mix(wrinkle_diff,wrinkle_result_blur,wrinkle_blur.a * wrinkle_alpha),1.0);
}