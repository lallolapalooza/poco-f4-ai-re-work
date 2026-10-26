varying vec2 texc;
varying vec2 texf;

vec3 darkeyecircle(vec3 incolor,vec3 averColor,vec3 averColor_blur,vec2 dd_texv,vec4 darkeyecircle_color,vec4 darkeyecircle_blur_color){
  float rate = 1.0;

  vec3 oColorTemp = min(averColor + darkeyecircle_color.rgb, 1.0);
  vec3 outcolor = oColorTemp;

  vec3 oColorTemp_blur = min(averColor_blur + darkeyecircle_blur_color.rgb, 1.0);
  vec3 outcolor_blur = oColorTemp_blur;

  outcolor = mix(outcolor,outcolor_blur,blur_rate);
  outcolor = mix(incolor,outcolor,darkeyecircle_rate * 1.0 * darkeyecircle_color.a * rate);
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec4 raw_color = texture2D(tex_raw, texc);
  vec4 wrinkle_color = raw_color;
  if (texf.x >= offset_pad.x && texf.x <= offset_pad.x + wh_pad.x && texf.y >= offset_pad.y &&
    texf.y <= offset_pad.y + wh_pad.y) {
      vec2 dd_texv = texf - offset_pad;
      dd_texv.x /= wh_pad.x;
      dd_texv.y /= wh_pad.y;
      wrinkle_color.rgb = raw_color.rgb + texture2D(tex_wrikle, dd_texv).rgb - 0.5;
      wrinkle_color.rgb = clamp(wrinkle_color.rgb,0.0,1.0);
  }


  vec3 oColor = origin_color.rgb;
  vec3 outcolor_wrinkle = oColor.rgb;
  vec3 outcolor_normal = oColor.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 dd_texv = texf - offset;
    dd_texv.x /= wh.x;
    dd_texv.y /= wh.y;

    // wrinkle
    vec3 noacen_color = texture2D(tex_noacen_wrinkle, dd_texv).rgb - 0.5 + wrinkle_color.rgb;
    noacen_color = clamp(noacen_color,0.0,1.0);

    vec3 aver_color = texture2D(tex_aver_wrinkle, dd_texv).rgb;
    aver_color = clamp(noacen_color + aver_color - 0.5,0.0,1.0);

    vec3 aver_blur_color = texture2D(tex_aver_blur_wrinkle, texf).rgb;

		float rate = 1.0;
		
    if(darkeyecircle_rate > 0.001){
      vec4 darkeyecircle_color = texture2D(tex_darkeyecircle_wrinkle, dd_texv);
      vec4 darkeyecircle_blur_color = texture2D(tex_darkeyecircle_blur_wrinkle, dd_texv);
      outcolor_wrinkle = darkeyecircle(outcolor_wrinkle,aver_color,aver_blur_color,dd_texv,darkeyecircle_color,darkeyecircle_blur_color);
    }

    // normal
    noacen_color = texture2D(tex_noacen, dd_texv).rgb - 0.5 + raw_color.rgb;
    noacen_color = clamp(noacen_color,0.0,1.0);

    aver_color = texture2D(tex_aver, dd_texv).rgb;
    aver_color = clamp(noacen_color + aver_color - 0.5,0.0,1.0);

    aver_blur_color = texture2D(tex_aver_blur, texf).rgb;
	
    if(darkeyecircle_rate > 0.001){
      vec4 darkeyecircle_color = texture2D(tex_darkeyecircle, dd_texv);
      vec4 darkeyecircle_blur_color = texture2D(tex_darkeyecircle_blur, dd_texv);
      outcolor_normal = darkeyecircle(outcolor_normal,aver_color,aver_blur_color,dd_texv,darkeyecircle_color,darkeyecircle_blur_color);
    }
  }
  vec3 outcolor = mix(outcolor_normal,outcolor_wrinkle,wrikle_rate);
  gl_FragColor = vec4(outcolor, origin_color.a);
}