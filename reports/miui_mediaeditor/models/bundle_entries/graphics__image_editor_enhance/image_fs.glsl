varying vec2 texc;
void main() {
  if(type < 0.5){
    gl_FragColor = texture2D(tex_in,texc);
  }else if(type < 1.5){
    gl_FragColor = mix(texture2D(tex_in,texc),texture2D(tex_blur,texc),0.3);
  }else if(type < 2.5){
    vec4 incolor = texture2D(tex_blur,texc);
    vec4 mask_color = texture2D(tex_in,texc);
    gl_FragColor = vec4(mix(incolor.rgb,vec3(1.0,0.0,0.0),mask_color.r),mask_color.r);
  }else if(type < 3.5){
    gl_FragColor = vec4(texture2D(tex_in,texc).rgb,0.0);
  }else{
    gl_FragColor = vec4(texture2D(tex_in,texc).a);
  }
}