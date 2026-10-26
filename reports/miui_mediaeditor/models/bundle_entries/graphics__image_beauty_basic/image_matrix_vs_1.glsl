varying vec2 texc;
void main() {
  texc = Pquad;
  vec2 Pquad1;
  Pquad1 = vec2(Pquad.x, Pquad.y);
  Pquad1 *= in_wh;
  Pquad1 = (model * vec4(Pquad1,0.0,1.0)).xy;
  Pquad1 /= out_wh;
  gl_Position = vec4(Pquad1 * 2.0 - 1.0, 0.0, 1.0);
}