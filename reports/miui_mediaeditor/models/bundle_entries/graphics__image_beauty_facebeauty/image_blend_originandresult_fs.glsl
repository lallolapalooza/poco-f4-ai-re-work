varying vec2 texc;

void main() {
  //int face_idx = int(texture2D(tex_facelabel, texc).r * 255.0 + 0.1);
  //float result_rate = arrfloat_result_rate[face_idx];
  vec4 color_origin = texture2D(tex_origin, texc);
  vec4 color_result = texture2D(tex_result, texc);
  gl_FragColor =
      color_origin * (1.0 - result_rate) + color_result * result_rate;
}