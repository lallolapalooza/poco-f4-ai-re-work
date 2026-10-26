varying vec2 texc;
void main() {
  vec3 in_color = texture2D(tex_in,texc).rgb;
  vec3 out_color = texture2D(tex_out,texc).rgb;
  gl_FragColor = vec4(out_color - in_color + 0.5, 1.0); 
}