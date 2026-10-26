varying vec2 texc;
void main() {
  vec3 ori_result = texture2D(tex_ori_result,texc).rgb;
  vec3 aver_result = texture2D(tex_aver_result,texc).rgb;
  vec3 noacen_result = texture2D(tex_noacen_result,texc).rgb;
  vec3 noacen_detail = clamp(noacen_result - ori_result + 0.5,0.0,1.0);
  vec3 aver_detail = clamp(aver_result - ori_result + 0.5,0.0,1.0);
  gl_FragColor = vec4(clamp(aver_detail - noacen_detail + 0.5,0.0,1.0),1.0);
}