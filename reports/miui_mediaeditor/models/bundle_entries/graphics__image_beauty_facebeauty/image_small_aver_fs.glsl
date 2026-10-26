varying vec2 texc;
void main() {
  vec3 ori_color = texture2D(tex_ori,texc).rgb;
  vec3 noacen_diff = texture2D(tex_noacen_diff,texc).rgb;
  vec3 aver_diff = texture2D(tex_aver_diff,texc).rgb;
  vec3 noacen_color = clamp(ori_color + noacen_diff - 0.5,0.0,1.0);
  vec3 aver_color = clamp(noacen_color + aver_diff - 0.5,0.0,1.0);
  gl_FragColor = vec4(aver_color,1.0);
}