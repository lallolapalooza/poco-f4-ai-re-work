varying vec2 texc;
varying vec2 texf;

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec4 raw_color = texture2D(tex_raw, texc);
  vec3 oColor = origin_color.rgb;
  vec3 outcolor = oColor.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 dd_texv = texf - offset;
    dd_texv.x /= wh.x;
    dd_texv.y /= wh.y;
    vec4 darkeyecircle_decreepattern_color = texture2D(tex_darkeyecircle_decreepattern, dd_texv);
    vec4 darkeyecircle_decreepattern_blur_color = texture2D(tex_darkeyecircle_decreepattern_blur, dd_texv);
    vec3 shdif = mix(darkeyecircle_decreepattern_color.rgb,darkeyecircle_decreepattern_blur_color.rgb,blur_rate);   
    vec3 aver_color = texture2D(tex_aver, dd_texv).rgb;
    vec3 noacen_color = texture2D(tex_noacen, dd_texv).rgb - 0.5 + raw_color.rgb;
    noacen_color = clamp(noacen_color,0.0,1.0);
    aver_color = clamp(noacen_color + aver_color - 0.5,0.0,1.0);
    vec4 aver_blur_color = texture2D(tex_aver_blur, texf);
    vec3 averColor = mix(aver_color.rgb,aver_blur_color.rgb,blur_rate);

    float minus_alpha = texture2D(tex_protect_manual_mask,texf).a;
		float rate = 1.0;
		
    if(type < 0.5){
        if(minus_alpha < 1.0){
          rate *=  0.3;
        }
        averColor = mix(oColor, averColor, 0.5);         
        vec3 oColorTemp = min(averColor + shdif, 1.0);
        shdif = oColorTemp;
        //shdif = min(shdif, max(max(averColor, hbColor), oColorTemp));
        outcolor = mix(averColor, shdif, 0.8 * 0.6);
        outcolor = mix(oColor,outcolor,darkeyecircle_decreepattern_rate * darkeyecircle_decreepattern_color.a * rate);
    }
    else{
        averColor = mix(oColor, averColor, 1.0);         
        vec3 oColorTemp = min(averColor + shdif, 1.0);
        shdif = oColorTemp;
        //shdif = min(shdif, max(max(averColor, hbColor), oColorTemp));
        outcolor = mix(oColor, shdif, darkeyecircle_decreepattern_rate * 1.0 * darkeyecircle_decreepattern_color.a * rate);
    }
  }

  gl_FragColor = vec4(outcolor, origin_color.a);
}