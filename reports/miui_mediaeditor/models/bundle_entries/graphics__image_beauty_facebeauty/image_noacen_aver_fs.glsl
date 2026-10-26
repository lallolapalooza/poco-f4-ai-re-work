
varying vec2 texc;
varying vec2 texf;

vec3 noacen_aver(vec3 incolor){
  vec3 outcolor = incolor.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 noav_texv = texf - offset;
    noav_texv.x /= wh.x;
    noav_texv.y /= wh.y;
    vec4 alpha = texture2D(tex_mask,noav_texv);

    // norm
    vec4 noacen_detail_color = texture2D(tex_noacen, noav_texv);
    vec3 noacen_color = clamp(noacen_detail_color.rgb - 0.5 + incolor.rgb,0.0,1.0);
    vec3 outcolor_norm = mix(incolor.rgb, noacen_color, noacen_rate * noacen_detail_color.a);

    vec3 aver_detail_color = texture2D(tex_aver, noav_texv).rgb;
    vec3 aver_color = clamp(noacen_color + aver_detail_color - 0.5,0.0,1.0);
    outcolor_norm = mix(outcolor_norm,aver_color,aver_rate * alpha.b);

    // wrikle
    vec3 wrikle_detail_color = texture2D(tex_wrikle, noav_texv).rgb;
    vec3 outcolor_wrikle = clamp(incolor.rgb + wrikle_detail_color - 0.5,0.0,1.0);

    vec4 wrikle_noacen_detail_color = texture2D(tex_wrikle_noacen, noav_texv);
    vec3 wrikle_noacen_color = clamp(wrikle_noacen_detail_color.rgb - 0.5 + outcolor_wrikle.rgb,0.0,1.0);
    outcolor_wrikle = mix(outcolor_wrikle.rgb, wrikle_noacen_color, noacen_rate * noacen_detail_color.a);

    vec3 wrikle_aver_detail_color = texture2D(tex_wrikle_aver, noav_texv).rgb;
    vec3 wrikle_aver_color = clamp(wrikle_noacen_color + wrikle_aver_detail_color - 0.5,0.0,1.0);
    outcolor_wrikle = mix(outcolor_wrikle,wrikle_aver_color,aver_rate * alpha.b);

    outcolor = mix(outcolor_norm,outcolor_wrikle,wrikle_rate * alpha.b);
  }
  return outcolor;
}

vec3 noacen_aver_wrinkle(vec3 incolor){
  vec3 outcolor = incolor.rgb;
  vec3 outcolor_norm = outcolor;
  vec3 outcolor_wrikle = outcolor;
  vec4 alpha = vec4(0.0);

  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 noav_texv = texf - offset;
    noav_texv.x /= wh.x;
    noav_texv.y /= wh.y;
    alpha = texture2D(tex_mask,noav_texv);

    // norm
    vec4 noacen_detail_color = texture2D(tex_noacen, noav_texv);
    vec3 noacen_color = clamp(noacen_detail_color.rgb - 0.5 + incolor.rgb,0.0,1.0);
    outcolor_norm = mix(incolor.rgb, noacen_color, noacen_rate * alpha.b);

    vec3 aver_detail_color = texture2D(tex_aver, noav_texv).rgb;
    vec3 aver_color = clamp(noacen_color + aver_detail_color - 0.5,0.0,1.0);
    outcolor_norm = mix(outcolor_norm,aver_color,aver_rate * alpha.b);
  }

  if (texf.x >= offset_pad.x && texf.x <= offset_pad.x + wh_pad.x && texf.y >= offset_pad.y &&
      texf.y <= offset_pad.y + wh_pad.y) {
    vec2 noav_texv = texf - offset_pad;
    noav_texv.x /= wh_pad.x;
    noav_texv.y /= wh_pad.y;

    // wrikle
    vec3 wrikle_detail_color = texture2D(tex_wrikle, noav_texv).rgb;
    outcolor_wrikle = clamp(incolor.rgb + wrikle_detail_color - 0.5,0.0,1.0);

    vec4 wrikle_noacen_detail_color = texture2D(tex_wrikle_noacen, noav_texv);
    vec3 wrikle_noacen_color = clamp(wrikle_noacen_detail_color.rgb - 0.5 + outcolor_wrikle.rgb,0.0,1.0);
    outcolor_wrikle = mix(outcolor_wrikle.rgb, wrikle_noacen_color, noacen_rate * alpha.b);

    vec3 wrikle_aver_detail_color = texture2D(tex_wrikle_aver, noav_texv).rgb;
    vec3 wrikle_aver_color = clamp(wrikle_noacen_color + wrikle_aver_detail_color - 0.5,0.0,1.0);
    outcolor_wrikle = mix(outcolor_wrikle,wrikle_aver_color,aver_rate * alpha.b);
  }

  outcolor = mix(outcolor_norm,outcolor_wrikle,wrikle_rate);
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;
  if(wrinkle_type < 0.5){
      outcolor = noacen_aver(outcolor);
    }else{
      outcolor = noacen_aver_wrinkle(outcolor);
    }
  gl_FragColor = vec4(outcolor, origin_color.a);
}
