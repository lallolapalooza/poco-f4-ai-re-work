varying vec2 texc;
void main() { 
  float dis = distance(texc,vec2(0.5,0.5));
  float result_color = 0.0;
  if(dis < 0.5){
    result_color = smoothstep(0.5,blur,dis);
  }
  gl_FragColor = vec4(vec3(alpha),result_color);
}