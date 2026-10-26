varying vec2 texc;

void main(){
  vec4 color = texture2D(tex_mask,texc);
  //gl_FragColor = color;
  if(color.r > 0.001){
    gl_FragColor = vec4(1.0,1.0,1.0,1.0);
  }
  else{
    gl_FragColor = vec4(0.0,0.0,0.0,1.0);
  }
}
