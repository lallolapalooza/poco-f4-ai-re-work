varying vec2 texc;
varying vec2 texf;

vec3 blur(vec3 incolor){
  int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
  vec4 blur_aver_color = texture2D(tex_blur_aver, texf);
  vec4 blur_noacen_color = texture2D(tex_blur_noacen, texf);
  vec4 blur_origin_color = texture2D(tex_blur_origin, texf);
  vec4 blur_aver_wrikle_color = texture2D(tex_blur_aver_wrikle, texf);
  vec4 blur_noacen_wrikle_color = texture2D(tex_blur_noacen_wrikle, texf);
  vec4 blur_wrikle_color = texture2D(tex_blur_wrikle, texf);

  vec3 blur_color;
  if(face_idx > 0 && face_idx <= int(face_num)){
    float aver_rate = arrfloat_aver_rate[face_idx];
    float noacen_rate = arrfloat_noacen_rate[face_idx];
    if(noacen_rate < 0.001) {
      noacen_rate = 0.0;
    }
    else {
      noacen_rate = 1.0;
    }
    float wrikle_rate = arrfloat_wrikle_rate[face_idx];

    vec3 blur_color_result = blur_origin_color.rgb;
    vec3 blur_color_wrikle_result = blur_wrikle_color.rgb;

    blur_color_result = mix(blur_color_result,blur_noacen_color.rgb,noacen_rate);
    blur_color_result = mix(blur_color_result,blur_aver_color.rgb,aver_rate);

    blur_color_wrikle_result = mix(blur_color_wrikle_result,blur_noacen_wrikle_color.rgb,noacen_rate);
    blur_color_wrikle_result = mix(blur_color_wrikle_result,blur_aver_wrikle_color.rgb,aver_rate);

    blur_color = mix(blur_color_result,blur_color_wrikle_result,wrikle_rate);
  }else{
    blur_color = blur_aver_color.rgb;
  } 

  float minus_alpha = 1.0;
  if(blur_nevus_rate > 0.5){
    minus_alpha *= texture2D(tex_protect_manual_mask,texf).a;
  }

  float alpha_source = 0.0;
  vec3 outcolor;
  alpha_source = blur_aver_color.a * blur_rate * blur_rate_scale * minus_alpha;
  outcolor = mix(incolor,blur_color.rgb,alpha_source);
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;
  if(blur_rate > 0.001){
    outcolor = blur(outcolor);
  }
  gl_FragColor = vec4(outcolor, origin_color.a);
}