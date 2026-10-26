varying vec2 texc;
void main() {
  float result_color = 0.0;
  float two_line_dis = abs(b1 - b2) / sqrt(1.0 + k * k);
  float b1_t = b1;
  float b2_t = b2;
  vec2 texc_flipy = vec2(texc.x, 1.0 - texc.y);
  texc_flipy *= wh;
  if(reflection < 0.5){
    float y = texc_flipy.y - k * texc_flipy.x - b2_t;
    float line_dis = abs(k * texc_flipy.x - texc_flipy.y + b2_t) / sqrt(1.0 + k * k);
    if((b1_t > b2_t && y > 0.001) || (b1_t <= b2_t && y < 0.001)){
      result_color = smoothstep(0.0,two_line_dis,line_dis);
    }
  }else{
    float line_dis = abs(k * texc_flipy.x - texc_flipy.y + (b1_t + b2_t) / 2.0) / sqrt(1.0 + k * k);
    result_color = 1.0 - smoothstep(two_line_dis / 2.0,0.0,line_dis);
  }
  if(invertion > 0.5){
     result_color = 1.0 - result_color;
  }
  vec4 in_color = texture2D(tex_in,texc);
  gl_FragColor = vec4(mix(in_color.rgb,vec3(1.0,0.0,0.0),result_color),result_color);
}