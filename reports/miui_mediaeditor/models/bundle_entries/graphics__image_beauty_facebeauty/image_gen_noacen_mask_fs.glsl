
varying vec2 texc;

void main() {
  vec3 noacen_color = texture2D(tex_noacen, texc).rgb;
  float alpha = 0.0;
  if(type < 0.5){
    alpha = texture2D(tex_mask, texc).a;
  }else{
    alpha = texture2D(tex_mask, texc).r;
  }
  gl_FragColor = vec4(noacen_color, alpha);
}