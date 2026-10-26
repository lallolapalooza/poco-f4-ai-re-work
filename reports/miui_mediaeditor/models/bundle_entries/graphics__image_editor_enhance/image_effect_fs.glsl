varying vec2 texc;
varying vec2 texf;

vec3 do_brightness(vec3 in_color) {
	vec3 out_color = in_color;
	if (abs(brightness) < 0.001) {
		return out_color;
	} else {
		float value = step(0.0, brightness) * 2.0 - 1.0;
		float fac = 1.0 / (value * 0.55);
		float fac2 = 1.0 - fac;
		vec3 tmp1 = fac2 - out_color.rgb;
		vec3 tmp2 = sqrt(fac2 * fac2 + (4.0 * fac) * out_color.rgb);
		
		out_color.rgb = tmp1 + tmp2 * value;
		
		return clamp(mix(in_color,out_color,abs(brightness)),0.0,1.0);
	}
}

vec3 do_exposure(vec3 in_color) {
	if (abs(exposure) < 0.001){
		return in_color;
	}
	float value = (step(0.0, exposure) * 2.0 - 1.0) * 0.5;
	vec3 result_color = clamp(in_color * exp2(1.8 * value),0.0,1.0);
	return mix(in_color,result_color,abs(exposure));
	// float value = exposure * 1.8;
	// return clamp(in_color * exp2(value),0.0,1.0);
}

vec3 do_contrast(vec3 in_color){
	if (abs(contrast) < 0.001){
		return in_color;
	}
	float value = 0.25;
	if(contrast < 0.0){
		value = -0.15;
	}
	vec3 out_color = in_color;
	float cont = tan((value + 1.0) * 3.1415926535 * 0.25);
	out_color.rgb = (out_color.rgb - vec3(0.5)) * cont + vec3(0.5);
	return clamp(mix(in_color,out_color,abs(contrast)),0.0,1.0);
}

vec3 do_highlight(vec3 in_color) {
	if (abs(highlight) < 0.001){
		return in_color;
	}
	float value = (step(0.0, highlight) * 2.0 - 1.0);
	vec3 src = in_color;
	vec3 color = src;
	float l = tan(value * (-1.1));
	if(src.r >= 0.5){
		src.r = 4.0 * (l - 1.0) * src.r * src.r * src.r + 8.0 * (1.0 - l) * src.r * src.r + (5.0 * l - 4.0) * src.r + 1.0 - l;
		src.r = mix(color.r, src.r, abs(highlight));
	}

	if(src.g >= 0.5){
		src.g = 4.0 * (l - 1.0) * src.g * src.g * src.g + 8.0 * (1.0 - l) * src.g * src.g + (5.0 * l - 4.0) * src.g + 1.0 - l;
		src.g = mix(color.g, src.g, abs(highlight));
	}

	if(src.b >= 0.5){
		src.b = 4.0 * (l - 1.0) * src.b * src.b * src.b + 8.0 * (1.0 - l) * src.b * src.b + (5.0 * l - 4.0) * src.b + 1.0 - l;
		src.b = mix(color.b, src.b, abs(highlight));
	}
	return clamp(src,0.0,1.0);
}

vec3 do_shadow(vec3 in_color) {
	if (abs(shadow) < 0.001){
		return in_color;
	}
	float value = (step(0.0, shadow) * 2.0 - 1.0);
	vec3 src = in_color;
	vec3 color = src;
	float d = tan(value * 1.2);
	if(src.r < 0.5){
		src.r = 4.0 * (d - 1.0) * src.r * src.r * src.r + 4.0 * (1.0 - d) * src.r * src.r + d * src.r;;
		src.r = mix(color.r, src.r, abs(shadow));
	}

	if(src.g < 0.5){
		src.g = 4.0 * (d - 1.0) * src.g * src.g * src.g + 4.0 * (1.0 - d) * src.g * src.g + d * src.g;
		src.g = mix(color.g, src.g, abs(shadow));
	}

	if(src.b < 0.5){
		src.b = 4.0 * (d - 1.0) * src.b * src.b * src.b + 4.0 * (1.0 - d) * src.b * src.b + d * src.b;
		src.b = mix(color.b, src.b, abs(shadow));
	}
	return clamp(src,0.0,1.0);
}

vec4 lut_color (vec4 C,sampler2D tex_lut){
	float color_scale_s_color_custom_shader_whitten = 1.0;
	float raw_s_color_custom_shader_whitten = 8.0;
	float col_s_color_custom_shader_whitten = 8.0;
	float dim_s_color_custom_shader_whitten = 64.0;
	vec4 textureColor = C;
	textureColor *= color_scale_s_color_custom_shader_whitten  ;
	float blueColor = textureColor.b * (dim_s_color_custom_shader_whitten   - 1.0);
	float r_raw = 1.0 / raw_s_color_custom_shader_whitten  ;
	float r_col = 1.0 / col_s_color_custom_shader_whitten  ;
	vec2 quad1;
	quad1.y = floor(floor(blueColor) / raw_s_color_custom_shader_whitten  );
	quad1.x = floor(blueColor) - (quad1.y * col_s_color_custom_shader_whitten  );
	vec2 quad2; 
	quad2.y = floor(ceil(blueColor) / raw_s_color_custom_shader_whitten  );
	quad2.x = ceil(blueColor) - (quad2.y * col_s_color_custom_shader_whitten  );
	vec2 texPos1;      
	texPos1.x = (quad1.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos1.y = (quad1.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec2 texPos2;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	texPos2.x = (quad2.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos2.y = (quad2.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec4 newColor1 = texture2D(tex_lut  , texPos1);
	vec4 newColor2 = texture2D(tex_lut  , texPos2);
	vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
	return vec4(newColor1.rgb,C.a);
}
float minmax_aver(vec3 incolor) {
  return (min(min(incolor.r, incolor.g), incolor.b) + max(max(incolor.r, incolor.g), incolor.b)) / 2.0;
}
vec3 sRGBGamma(vec3 col){
    vec3 sV = step(col,vec3(0.0031308));
    return mix(max(1.055 * pow (col, vec3(1.0/2.4)) - 0.055,vec3(0.0)),(col*12.92),sV);
}
vec3 invSRGBGamma(vec3 col){
    vec3 sV = step(col,vec3(0.040449936));
    return mix(max(pow ((col + 0.055)/1.055, vec3(2.4)),vec3(0.0)),(col/12.92),sV);
}
const mat3 fromSRGBMatr = mat3(0.613195, 0.070205, 0.020619, 
                              0.339515, 0.916334, 0.109567,
                              0.047368, 0.013449, 0.869606);
const mat3 toSRGBMatr = mat3(1.70478, -0.13026, -0.024009, 
                            -0.621692, 1.140829, -0.129,
                            -0.083245, -0.010549, 1.153248);
vec3 do_whiteblack(vec3 incolor) {
  if (abs(white) < 0.001 && abs(black) < 0.001){
    return incolor;
  }
  vec3 result_color = incolor;
  if(white >= 0.0){
    result_color = mix(result_color,lut_color(vec4(result_color,1.0),tex_white100).rgb,white);
  }

  result_color = fromSRGBMatr * invSRGBGamma(result_color);
  result_color = pow(result_color,vec3(1.0 / 2.2));

  float gray = sqrt(minmax_aver(result_color));
  vec4 lut_color = texture2D(tex_whiteblack, vec2(gray, 0.5));

  if(white >= 0.0){
    //result_color = result_color * (lut_color.r * white + gray) / gray;
  }else{
    result_color = result_color * (gray + lut_color.g * white * 2.0) / gray;
  }
  if(black >= 0.0){
    result_color = result_color * (lut_color.b * black * 2.0 + gray) / gray;
  }else{
    result_color = result_color * (gray + lut_color.a * black) / gray;
  }

  result_color = pow(result_color,vec3(2.2));
  result_color = sRGBGamma(toSRGBMatr * result_color);
  return clamp(result_color, 0.0, 1.0);
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
	vec4 o_color = incolor;
	incolor.rgb = do_exposure(incolor.rgb);
	incolor.rgb = do_brightness(incolor.rgb);
	incolor.rgb = do_contrast(incolor.rgb);
	incolor.rgb = do_highlight(incolor.rgb);
	incolor.rgb = do_shadow(incolor.rgb);
	incolor.rgb = do_whiteblack(incolor.rgb);
	vec4 mask_color = texture2D(tex_mask,texf);
	gl_FragColor = vec4(mix(o_color.rgb,incolor.rgb,clamp(mask_color.a,0.0,1.0)), incolor.a);
}
