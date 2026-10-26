varying vec2 texc;
void main() {
  float min_dark = 1.0;
  vec4 o_color = texture2D(tex_origin, texc);
  float o_min = min(min(min(1., o_color.r), o_color.g), o_color.b);
  for (float i = -8.0; i <= 8.0; ++i) {
    vec4 i_color = texture2D(tex_origin, texc + delta * i / 8.0);
    min_dark = min(min(min(min_dark, i_color.r), i_color.g), i_color.b);
  }
  gl_FragColor = vec4(min_dark, 0., 0., o_min);
}