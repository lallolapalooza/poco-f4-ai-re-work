varying vec2 texc;
void main() {
  vec4 in_color = texture2D(tex_in,texc);
  gl_FragColor = 1.0 - clamp(in_color,0.0,1.0);
}