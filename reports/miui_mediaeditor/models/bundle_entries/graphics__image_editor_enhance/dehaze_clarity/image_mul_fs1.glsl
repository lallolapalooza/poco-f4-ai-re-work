varying vec2 texc;
void main() {
  vec3 o_color = texture2D(tex_origin, texc).rgb;
  gl_FragColor = vec4(o_color * o_color, 1.0);
}