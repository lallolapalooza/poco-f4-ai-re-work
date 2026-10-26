varying vec2 texc;

void main() {
  vec4 color_in = texture2D(tex_in, texc);
  vec4 color_mask = texture2D(tex_mask, texc);
  if(type < 0.5){
    gl_FragColor = vec4(color_in.a);
  }else if(type < 1.5){
    gl_FragColor = vec4(mix(color_in.rgb,vec3(1.0,0.0,0.0),color_mask.r),color_mask.r);
  }else{
    gl_FragColor = color_in;
  }

}