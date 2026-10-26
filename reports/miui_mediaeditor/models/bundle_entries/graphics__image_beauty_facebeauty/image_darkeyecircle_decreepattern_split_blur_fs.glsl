varying vec2 texc;
varying vec2 texf;

vec3 decreepattern(vec3 incolor,vec3 originColor,vec2 dd_texv){
  vec4 decreepattern_color = texture2D(tex_decreepattern, dd_texv);
  vec4 decreepattern_color_blur = texture2D(tex_decreepattern_blur, dd_texv);
  decreepattern_color.rgb = mix(decreepattern_color.rgb,decreepattern_color_blur.rgb,blur_rate);
  float rate = 1.0;

  vec3 oColorTemp = min(originColor + decreepattern_color.rgb, 1.0);
  vec3 outcolor = mix(originColor, oColorTemp, 0.8 * 0.6);

  outcolor = mix(incolor,outcolor,decreepattern_rate * decreepattern_color.a * rate);
  return outcolor;
}

vec3 darkeyecircle(vec3 incolor,vec3 originColor,vec2 dd_texv){
  vec4 darkeyecircle_color = texture2D(tex_darkeyecircle, dd_texv);
  vec4 darkeyecircle_color_blur = texture2D(tex_darkeyecircle_blur, dd_texv);
  darkeyecircle_color.rgb = mix(darkeyecircle_color.rgb,darkeyecircle_color_blur.rgb,blur_rate);

  float rate = 1.0;

  vec3 oColorTemp = min(originColor + darkeyecircle_color.rgb, 1.0);
  vec3 outcolor = oColorTemp;

  outcolor = mix(incolor,outcolor,darkeyecircle_rate * 1.0 * darkeyecircle_color.a * rate);
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 dd_texv = texf - offset;
    dd_texv.x /= wh.x;
    dd_texv.y /= wh.y;
 
    if(decreepattern_rate > 0.001){
      outcolor = decreepattern(outcolor,origin_color.rgb,dd_texv);
    }
    if(darkeyecircle_rate > 0.001){
      outcolor = darkeyecircle(outcolor,origin_color.rgb,dd_texv);
    }
  }

  gl_FragColor = vec4(outcolor, origin_color.a);
}