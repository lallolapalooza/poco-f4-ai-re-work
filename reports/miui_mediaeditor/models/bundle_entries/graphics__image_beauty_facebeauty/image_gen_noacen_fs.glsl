
varying vec2 texc;

void main() {
  vec3 origin_color = texture2D(tex_origin, texc).rgb;
  vec3 noacen_color = texture2D(tex_noacen, texc).rgb;
  gl_FragColor = vec4(clamp(noacen_color - 0.5 + origin_color,0.0,1.0), 1.0);
}