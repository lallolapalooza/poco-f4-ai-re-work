varying vec2 texc;
void main() {
  float dark_min = 1.0;
  vec4 o_color = texture2D(tex_origin, texc);
  for (float i = -8.0; i <= 8.0; ++i) {
    dark_min = min(dark_min, texture2D(tex_origin, texc + delta * i / 8.0).r);
  }
  dark_min = 1.0 - clamp(1.0 - 0.95 * (dark_min / .95), 0.0, 1.0);
  gl_FragColor = vec4(dark_min, dark_min * o_color.a, o_color.a, o_color.a * o_color.a);
}