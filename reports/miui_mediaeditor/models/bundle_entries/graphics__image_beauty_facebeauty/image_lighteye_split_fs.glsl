
varying vec2 texc;
varying vec2 texf;

vec3 saturation_change(vec3 source, float saturation) {
    vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);
    float luminance = dot(source.rgb, luminanceWeighting);
    vec3 greyScaleColor = vec3(luminance);
    return mix(greyScaleColor, source.rgb, saturation);
}

vec3 eye_curve(vec3 color,float rate)
{
    float new_o_r = texture2D(tex_eye_curve_mask,vec2(color.r,0.0)).r;
    float new_o_g = texture2D(tex_eye_curve_mask,vec2(color.g,0.0)).r;
    float new_o_b = texture2D(tex_eye_curve_mask,vec2(color.b,0.0)).r;
    vec3 new_color = vec3(new_o_r,new_o_g,new_o_b);
    new_color = clamp(new_color,0.0,1.0);
    return color + (new_color - color) * rate;
} 

vec3 lighteye(vec3 incolor){
  vec3 outcolor = incolor.rgb;

  vec2 ls_texv = texf - offset;
  ls_texv.x /= wh.x;
  ls_texv.y /= wh.y;

  if (texf.x >= offset_eye.x && texf.x <= offset_eye.x + wh_eye.x && texf.y >= offset_eye.y &&
      texf.y <= offset_eye.y + wh_eye.y) {
    vec2 eye_texv = texf - offset_eye; 
    eye_texv.x /= wh_eye.x;
    eye_texv.y /= wh_eye.y;

    vec4 color_lighteye = texture2D(tex_lighteye, eye_texv);
    vec3 color_lighteye_detail;
    if(is_preview_of_getresult < 0.5){
      color_lighteye_detail = texture2D(tex_lighteye_detail, eye_texv).rgb;
    }
    else{
      color_lighteye_detail = texture2D(tex_lighteye_detail, texc).rgb;
    }

    vec3 source_eyeblack = incolor.rgb;
    vec3 result_eyeblack = source_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack = mix(source_eyeblack, result_eyeblack, 0.65);
    vec3 result_eyeblack1 = result_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack1 = mix(result_eyeblack, result_eyeblack1, 0.325);
    //vec3 result_eyeblack1 = result_eyeblack;
    result_eyeblack = saturation_change(result_eyeblack1,1.15);

    vec3 source_eyewhite = color_lighteye.rgb;
    vec3 result_eyewhite = source_eyewhite + (color_lighteye_detail * 2.0 - 1.0); 
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.65);

    if (source_eyewhite.r > source_eyewhite.g) {
        source_eyewhite.g += 0.8*(source_eyewhite.r - source_eyewhite.g);
    }
    if (source_eyewhite.r > source_eyewhite.b) {
        source_eyewhite.b += 0.8*(source_eyewhite.r - source_eyewhite.b);
    }
    source_eyewhite = clamp(source_eyewhite,0.0,1.0);
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.3);
    result_eyewhite = eye_curve(result_eyewhite,0.65);

    vec2 pos_eye = ls_texv * pic_wh;
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    float dis_rate = 0.0;
    if (lefteye_dis <= righteye_dis) {
        dis_rate = lefteye_dis / left_iris_radius;
    } else {
        dis_rate = righteye_dis / right_iris_radius;
    }

    float eyeball_alpha = smoothstep(1.6, 0.8, dis_rate);
    vec3 origin_color = incolor.rgb;
    vec3 out_circle_color = mix(origin_color,result_eyewhite,1.0 - eyeball_alpha);
    dis_rate = smoothstep(1.1, 0.9, dis_rate);
    vec3 in_circle_color = result_eyeblack;
    vec3 result_color = in_circle_color * dis_rate + out_circle_color * (1.0 - dis_rate);

    float lighteyerate = lighteye_rate * color_lighteye.a;
    outcolor = mix(outcolor, result_color, lighteyerate);
  }
  return outcolor;
}

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec3 outcolor = origin_color.rgb;

  vec2 ls_texv = texf - offset;
  ls_texv.x /= wh.x;
  ls_texv.y /= wh.y;

  if (texf.x >= offset_eye.x && texf.x <= offset_eye.x + wh_eye.x && texf.y >= offset_eye.y &&
      texf.y <= offset_eye.y + wh_eye.y) {
    vec2 eye_texv = texf - offset_eye; 
    eye_texv.x /= wh_eye.x;
    eye_texv.y /= wh_eye.y;

    vec4 color_lighteye = texture2D(tex_lighteye, eye_texv);
    vec3 color_lighteye_detail;
    if(is_preview_of_getresult < 0.5){
      color_lighteye_detail = texture2D(tex_lighteye_detail, eye_texv).rgb;
    }
    else{
      color_lighteye_detail = texture2D(tex_lighteye_detail, texc).rgb;
    }

    vec3 source_eyeblack = origin_color.rgb;
    vec3 result_eyeblack = source_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack = mix(source_eyeblack, result_eyeblack, 0.65);
    vec3 result_eyeblack1 = result_eyeblack + (color_lighteye_detail * 2.0 - 1.0);
    result_eyeblack1 = mix(result_eyeblack, result_eyeblack1, 0.325);
    //vec3 result_eyeblack1 = result_eyeblack;
    result_eyeblack = saturation_change(result_eyeblack1,1.15);

    vec3 source_eyewhite = color_lighteye.rgb;
    vec3 result_eyewhite = source_eyewhite + (color_lighteye_detail * 2.0 - 1.0); 
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.65);

    if (source_eyewhite.r > source_eyewhite.g) {
        source_eyewhite.g += 0.8*(source_eyewhite.r - source_eyewhite.g);
    }
    if (source_eyewhite.r > source_eyewhite.b) {
        source_eyewhite.b += 0.8*(source_eyewhite.r - source_eyewhite.b);
    }
    source_eyewhite = clamp(source_eyewhite,0.0,1.0);
    result_eyewhite = mix(source_eyewhite, result_eyewhite, 0.3);
    result_eyewhite = eye_curve(result_eyewhite,0.65);

    vec2 pos_eye = ls_texv * pic_wh;
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    float dis_rate = 0.0;
    if (lefteye_dis <= righteye_dis) {
        dis_rate = lefteye_dis / left_iris_radius;
    } else {
        dis_rate = righteye_dis / right_iris_radius;
    }

    float eyeball_alpha = smoothstep(1.6, 0.8, dis_rate);
    vec3 origin_color = origin_color.rgb;
    vec3 out_circle_color = mix(origin_color,result_eyewhite,1.0 - eyeball_alpha);
    dis_rate = smoothstep(1.1, 0.9, dis_rate);
    vec3 in_circle_color = result_eyeblack;
    vec3 result_color = in_circle_color * dis_rate + out_circle_color * (1.0 - dis_rate);

    float lighteyerate = lighteye_rate * color_lighteye.a;
    outcolor = mix(outcolor, result_color, lighteyerate);
  }
  gl_FragColor = vec4(outcolor, origin_color.a);
}