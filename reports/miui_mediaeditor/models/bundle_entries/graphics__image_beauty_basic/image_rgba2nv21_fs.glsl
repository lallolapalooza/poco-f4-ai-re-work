// uniform u_offset
varying vec2 texc;

void main() {
  vec2 st = texc;
  vec2 st0, st1, st2, st3;
  vec4 m0, m1;
  float base;
  if (st.y < h_threshold) {
    st0 = st * vec2(1.0, h_scale0) + vec2((-1.5) * x_delta0, 0.0);
    st0.x = st0.x * w_scale0;
    st1 = st0 + vec2(x_delta0, 0.0);
    st2 = st0 + vec2(x_delta0 * 2.0, 0.0);
    st3 = st0 + vec2(x_delta0 * 3.0, 0.0);
    m0 = vec4(0.299, 0.587, 0.114, 0.0);
    m1 = vec4(0.299, 0.587, 0.114, 0.0);
    base = 0.0;
  } else {
    st0 = vec2(st.x, st.y - h_threshold) * vec2(1.0, h_scale1) +
          vec2((-1.0) * x_delta0, (-0.5) * y_delta0);
    // vec2((-1.0) * x_delta0, 0.0);
    st0.x = st0.x * w_scale0;
    st1 = st0;
    st2 = st0 + vec2(x_delta0 * 2.0, 0.0);
    st3 = st2;
    m0 = vec4(0.499, -0.418, -0.0813, 0.0);
    m1 = vec4(-0.169, -0.331, 0.499, 0.0);
    // m0 = vec4(0.0, 0.0, 0.0, 0.0);
    // m1 = vec4(0.0, 0.0, 0.0, 0.0);
    base = 0.5;
  }
  gl_FragColor = vec4(dot(texture2D(tex_rgba, st0), m0) + base,
                      dot(texture2D(tex_rgba, st1), m1) + base,
                      dot(texture2D(tex_rgba, st2), m0) + base,
                      dot(texture2D(tex_rgba, st3), m1) + base);
}