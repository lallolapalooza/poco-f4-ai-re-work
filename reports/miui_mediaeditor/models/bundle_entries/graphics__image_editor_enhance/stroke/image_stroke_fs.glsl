varying vec2 texc;
varying vec2 texo;
varying vec2 texm;

vec3 changeRGB(vec3 incolor) {
  return incolor * mat3(0.299, 0.587, 0.114,
                        -0.14713, -0.28886, 0.436,
                        0.615, -0.51499, -0.10001);
}

void main() {
  vec3 o_color = changeRGB(texture2D(tex_smooth,texm).rgb);
  vec3 n_color = changeRGB(texture2D(tex_smooth,texo).rgb);
  float dis_color = length(abs(o_color - n_color));
  dis_color = 1.0 - min(dis_color / 0.3, 1.0);

  // vec4 sobel_color = texture2D(tex_sobel,texo);
  float dis_v = distance(texc,vec2(0.5));
  vec4 circle_color = texture2D(tex_circle,texc);
  //circle_color.a = circle_color.a * smoothstep(0.5,0.0,dis_v) + (1.0 - smoothstep(0.5,0.0,dis_v)) * circle_color.a * dis_color;
  if(identify_edge > 0.5){
      circle_color.a *= dis_color;
  }
  gl_FragColor = circle_color;
}
