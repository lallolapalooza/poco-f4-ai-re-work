varying vec2 texc;
varying vec2 texf;
void main() {
  texc = vec2(Pquad.x, Pquad.y);
  texf = (texc * b_dim + b_offset) / f_dim;
  gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
}