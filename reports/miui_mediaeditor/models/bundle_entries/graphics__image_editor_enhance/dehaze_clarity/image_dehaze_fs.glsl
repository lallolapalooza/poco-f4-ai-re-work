varying vec2 texc;
void main() {
  vec4 b_color = texture2D(tex_b, texc);
  vec4 a_color = texture2D(tex_a, texc);
  vec4 I_color = texture2D(tex_I, texc);
  I_color.a = min(min(I_color.r, I_color.g), I_color.b);
  gl_FragColor = a_color * I_color + b_color;
}