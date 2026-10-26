varying vec2 texc;
void main() {
  vec4 a_color = texture2D(tex_a, texc);
  vec4 mean_I_color = texture2D(tex_mean_I, texc);
  vec4 mean_p_color = texture2D(tex_mean_p, texc);
  vec4 color1 = vec4(mean_I_color.rgb, mean_p_color.b);
  vec4 color2 = vec4(mean_I_color.rgb, mean_p_color.r);
  gl_FragColor = color2 - a_color * color1;
}