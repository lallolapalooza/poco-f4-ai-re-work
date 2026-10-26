varying vec2 texc;

void main() {
  texc = vec2(Pquad.x, Pquad.y);
  gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
}