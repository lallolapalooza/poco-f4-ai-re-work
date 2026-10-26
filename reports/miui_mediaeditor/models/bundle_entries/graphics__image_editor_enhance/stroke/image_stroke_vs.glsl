
varying vec2 texc;
varying vec2 texo;
varying vec2 texm;
void main() {
  gl_Position = vec4(Pquad.x, -1.0 * Pquad.y, 0.0, 1.0);
  texc = Texv;
  texo = (Pquad.xy + 1.0) / 2.0;
  texo.y = 1.0 - texo.y;
  texm = (Pquad.zw + 1.0) / 2.0;
  texm.y = 1.0 - texm.y;
}