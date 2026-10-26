varying vec2 texc;
void main() {
  vec3 ori_small = texture2D(tex_ori_small,texc).rgb;
  vec3 result = texture2D(tex_result,texc).rgb;
  float alpha = texture2D(tex_mole,texc).a;
  gl_FragColor = vec4(clamp(result - ori_small + 0.5,0.0,1.0),alpha);
}