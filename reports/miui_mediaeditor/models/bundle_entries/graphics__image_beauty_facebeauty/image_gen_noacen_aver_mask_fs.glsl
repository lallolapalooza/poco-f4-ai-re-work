
varying vec2 texc;

void main() {
  float noacen_color = texture2D(tex_noacen, texc).a;
  float aver_color = texture2D(tex_aver, texc).a;
  gl_FragColor = vec4(noacen_color, aver_color,1.0,1.0);
}