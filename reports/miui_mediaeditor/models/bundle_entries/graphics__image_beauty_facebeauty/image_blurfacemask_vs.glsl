varying vec2 texf;
void main() {
  vec2 texc = vec2(Pquad.x, Pquad.y);
  texf = texc;
  gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
}