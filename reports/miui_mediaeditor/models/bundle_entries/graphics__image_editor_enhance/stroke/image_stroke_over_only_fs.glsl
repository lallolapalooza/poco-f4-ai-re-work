varying vec2 texc;
void main() {
  float last_color = texture2D(tex_last,texc).r;
  float last_record = last_color;
  float now_color = texture2D(tex_now,texc).r;
  //float sobel_color = texture2D(tex_sobel,texc).r;
  float region_color = texture2D(tex_region,texc).r;
  // if(region_protect > 0.5 && region_color > 0.001){
  //   now_color = 0.0;
  // }
  float alpha_real = now_color / alpha;
  float last_result = last_color + region_color;
  if(add_erase > 0.5){
    float re1 = last_color + now_color;
    if(last_result <= 0.0){
      re1 = now_color - region_color;
    }
    float re2 = last_color + now_color;
    last_color = mix(re2,re1,alpha_real);
  }else{
    float re1 = last_color - now_color;
    if(last_result >= 1.0){
      re1 = 1.0 - now_color - region_color;
    }
    float re2 = last_color - now_color;
    last_color = mix(re2,re1,alpha_real);
  }
  if(region_protect > 0.5){
    last_color = mix(last_record,last_color,1.0 - region_color);
  }
  gl_FragColor = vec4(last_color);
}