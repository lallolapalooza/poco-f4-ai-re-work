//#version 120
// precision highp float;
// uniform mediump vec4 color;
// uniform float edgeStart;

void main() {
  // float dis = distance(gl_PointCoord, vec2(0.5));
  float dis = 0.35;
  if (dis > 0.5) {
    discard;
  }
  gl_FragColor =
      vec4(color.rgb, color.a * (1.0 - smoothstep(edgeStart, 0.5, dis)));
}