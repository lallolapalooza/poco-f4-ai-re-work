
// uniform float pointSize;
void main() {
  // vec2 pos = vec2(Pos.x, 1.0 - Pos.y);
  vec2 pos = vec2(Pquad);
  gl_Position = vec4(pos * 2.0 - 1.0, 0.0, 1.0);
  //gl_PointSize = pointSize;
}