varying vec2 texc;
void main() {
  vec4 last_color = texture2D(tex_last,texc);
  vec4 now_color = texture2D(tex_now,texc);
  //vec4 sobel_color = texture2D(tex_sobel,texc);
  if(add_erase > 0.5){
    // if(identify_edge > 0.5){
    //   last_color += now_color * sobel_color.r;
    // }else{
    //   last_color += now_color;
    // }
    last_color += now_color;
  }else{
    last_color -= now_color;
  }
  gl_FragColor = last_color;
}