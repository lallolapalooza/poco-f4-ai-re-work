varying vec2 texc;
void main() {
  vec4 in_color = texture2D(tex_in,texc);
  float color_dis = distance(in_color.rgb,rgb);
  float result_color = smoothstep(range,0.01,color_dis);
  if(enable_circle > 0.5){
    vec2 texc_flipy = vec2(texc.x, 1.0 - texc.y);
    texc_flipy *= wh;
    texc_flipy -= center;
    vec2 p_rot;
    p_rot.x = cosAngle * texc_flipy.x - sinAngle * texc_flipy.y;
    p_rot.y = sinAngle * texc_flipy.x + cosAngle * texc_flipy.y;

    float circle_dis = pow(p_rot.x,2.0)/pow(a,2.0) + pow(p_rot.y,2.0)/pow(b,2.0) - 1.0;
    result_color *= smoothstep(0.5,-0.5,circle_dis);
  }
  if(invertion > 0.5){
     result_color = 1.0 - result_color;
  }
  gl_FragColor = vec4(mix(in_color.rgb,vec3(1.0,0.0,0.0),result_color),result_color);
}