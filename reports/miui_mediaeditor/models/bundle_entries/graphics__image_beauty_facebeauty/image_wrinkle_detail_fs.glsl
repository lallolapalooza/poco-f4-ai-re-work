varying vec2 texc;
void main() {
  vec3 origin_color = texture2D(tex_ori,texc).rgb;
  vec3 noacen_diff_color = vec3(0.5);
  vec3 aver_diff_color = vec3(0.5);
  if (texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y &&
      texc.y <= offset.y + wh.y) {
    vec2 noav_texv = texc - offset;
    noav_texv.x /= wh.x;
    noav_texv.y /= wh.y;

    noacen_diff_color = texture2D(tex_noacen_diff,noav_texv).rgb;
    aver_diff_color = texture2D(tex_aver_diff,noav_texv).rgb;
  }
  vec3 wrinkle_diff_color = texture2D(tex_wrinkle_diff,texc).rgb;
  vec3 wrinkle_noacen_diff_color = texture2D(tex_wrinkle_noacen_diff,texc).rgb;
  vec3 wrinkle_aver_diff_color = texture2D(tex_wrinkle_aver_diff,texc).rgb;

  if(type < 0.5){
    vec3 noacen_color = clamp(origin_color + noacen_diff_color - 0.5,0.0,1.0);
    vec3 noacen_wrinkle_color = clamp(noacen_color + wrinkle_noacen_diff_color - 0.5,0.0,1.0);

    vec3 wrinkle_color = clamp(origin_color + wrinkle_diff_color - 0.5,0.0,1.0);
    gl_FragColor = vec4(clamp(noacen_wrinkle_color - wrinkle_color + 0.5,0.0,1.0),1.0);
  }else{
    vec3 noacen_color = clamp(origin_color + noacen_diff_color - 0.5,0.0,1.0);
    vec3 noacen_wrinkle_color = clamp(noacen_color + wrinkle_noacen_diff_color - 0.5,0.0,1.0);

    vec3 aver_color = clamp(noacen_color + aver_diff_color - 0.5,0.0,1.0);
    vec3 aver_wrinkle_color = clamp(aver_color + wrinkle_aver_diff_color - 0.5,0.0,1.0);

    gl_FragColor = vec4(clamp(aver_wrinkle_color - noacen_wrinkle_color + 0.5,0.0,1.0),1.0);
  }
}