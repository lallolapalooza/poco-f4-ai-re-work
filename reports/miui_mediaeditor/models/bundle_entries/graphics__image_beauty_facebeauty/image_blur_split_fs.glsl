varying vec2 texc;
varying vec2 texf;

vec3 blur(vec3 incolor){
  int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
  vec4 blur_origin_color = texture2D(tex_blur_origin, texf);

  float minus_alpha = 1.0;
  if(blur_nevus_rate > 0.5){
    minus_alpha *= texture2D(tex_protect_manual_mask,texf).a;
  }

  float alpha_source = 0.0;
  vec3 outcolor;
  alpha_source = blur_origin_color.a * blur_rate * blur_rate_scale * minus_alpha;
  outcolor = mix(incolor,blur_origin_color.rgb,alpha_source);
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