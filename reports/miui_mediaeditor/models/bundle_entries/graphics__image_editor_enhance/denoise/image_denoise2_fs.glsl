varying vec2 texc;
float gray(vec3 incolor) { 
  return dot(incolor, vec3(0.298839, 0.586811, 0.11435)); 
}
void main() {
  float gray_o = gray(texture2D(tex_origin, texc).rgb);
  float weight_sum = 0.0;
  vec3 color_sum = vec3(0.0);
  for (int i = -7; i <= 7; i++) {
    vec4 color_i = texture2D(tex_origin, texc + vec2(0.0, float(i) * delta));
    float gray_dis = gray(color_i.rgb) - gray_o;
    float weight = arrfloat_kernel[7 + i] * exp(-(gray_dis * gray_dis * 312.5));
    color_sum += color_i.rgb * (weight * color_i.a * 15.0);
    weight_sum += weight * color_i.a * 15.0;
  }
  gl_FragColor = vec4(color_sum / weight_sum, 1.0 / weight_sum * 2.0);
}