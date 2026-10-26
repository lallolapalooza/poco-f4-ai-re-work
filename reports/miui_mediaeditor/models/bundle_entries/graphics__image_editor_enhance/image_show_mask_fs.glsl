varying vec2 texc;
varying vec2 texf;
void main() {
  vec4 in_color = texture2D(tex_input,texc);
  vec4 mask_color = texture2D(tex_mask,texf);
  gl_FragColor = vec4(mix(in_color.rgb,vec3(1.0,0.0,0.0),clamp(mask_color.a,0.0,1.0)),1.0);
}