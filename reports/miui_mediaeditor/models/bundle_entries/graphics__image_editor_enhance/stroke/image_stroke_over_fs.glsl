varying vec2 texc;
void main() {
  vec4 last_color = texture2D(tex_last,texc);
  vec4 now_color = texture2D(tex_now,texc);
  if(add_erase > 0.5){
    // vec4 sobel_color = texture2D(tex_sobel,texc);
    // if(identify_edge > 0.5){
    //   last_color.a += now_color.r * sobel_color.r;
    // }else{
    //   last_color.a += now_color.r;
    // }
    last_color.a += now_color.r;
  }else{
    last_color.a -= now_color.r;
  }
  gl_FragColor = vec4(mix(texture2D(tex_in,texc).rgb,vec3(1.0,0.0,0.0),clamp(last_color.a,0.0,1.0)),last_color.a);
}