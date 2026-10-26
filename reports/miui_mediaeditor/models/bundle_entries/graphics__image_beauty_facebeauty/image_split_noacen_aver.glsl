
varying vec2 texc;

void main() {
  vec4 origin_color = texture2D(tex_result, texc * wh + offset);
  if(type < 0.5){
    gl_FragColor = vec4(origin_color.rgb, 1.0);
  }else{
    gl_FragColor = vec4(vec3(origin_color.a), 1.0);
  }
}