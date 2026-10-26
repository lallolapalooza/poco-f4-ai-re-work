varying vec2 texc;
void main() {
  vec4 in_color = texture2D(tex_in,texc);
  vec4 origin_color = texture2D(tex_origin,texc);
  gl_FragColor = vec4(mix(origin_color.rgb,vec3(1.0,0.0,0.0),1.0 - in_color.a),1.0 - in_color.a);
}