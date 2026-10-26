varying vec2 texc;
void main() {
  texc = vec2(Pquad.x, Pquad.y);
  texc *= in_wh;
  texc = (model * vec4(texc,0.0,1.0)).xy;
  texc /= out_wh;
  gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
}