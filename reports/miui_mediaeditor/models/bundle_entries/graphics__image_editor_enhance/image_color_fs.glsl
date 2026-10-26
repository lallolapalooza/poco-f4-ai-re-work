varying vec2 texc;
varying vec2 texf;

vec3 do_saturation(vec3 in_color) {
  if (abs(saturation - 1.0) < 0.001){
    return in_color;
  }
	float lum = (max(max(in_color.r, in_color.g),in_color.b) + min(min(in_color.r, in_color.g), in_color.b)) / 2.0;
	return clamp(mix(vec3(lum), in_color.rgb, saturation),0.0,1.0);
}

vec3 do_vibrance(vec3 in_color) {
  if (abs(vibrance) < 0.001){
    return in_color;
  }
  float value = 0.9;
  if(vibrance < 0.0){
    value = -1.0;
  }
	float average = (in_color.r + in_color.g + in_color.b) / 3.0;
  vec3 result_color = clamp(vec3(in_color.rgb + atan(in_color.rgb - average) * value / 1.5708),0.0,1.0);
	return mix(in_color,result_color,abs(vibrance));
}

vec3 do_temperature(vec3 in_color) {
  if (abs(temperature) < 0.001){
    return in_color;
  }
	const vec3 warmFilter = vec3(0.93, 0.54, 0.0);
  //const mat3 RGBtoYIQ = mat3(0.299, 0.587, 0.114, 0.596, -0.274, -0.322, 0.212, -0.523, 0.311);
  //const mat3 YIQtoRGB = mat3(1.0, 0.956, 0.621, 1.0, -0.272, -0.647, 1.0, -1.105, 1.702);

	vec3 source = in_color;

	//vec3 yiq = RGBtoYIQ * source.rgb; //adjusting tint
	// yiq.b = clamp(yiq.b + tint*0.5226*0.1, -0.5226, 0.5226);
	//vec3 rgb = YIQtoRGB * yiq;
  vec3 rgb = source;

	vec3 processed = vec3(
		(rgb.r < 0.5 ? (2.0 * rgb.r * warmFilter.r) : (1.0 - 2.0 * (1.0 - rgb.r) * (1.0 - warmFilter.r))), //adjusting temperature
		(rgb.g < 0.5 ? (2.0 * rgb.g * warmFilter.g) : (1.0 - 2.0 * (1.0 - rgb.g) * (1.0 - warmFilter.g))), 
		(rgb.b < 0.5 ? (2.0 * rgb.b * warmFilter.b) : (1.0 - 2.0 * (1.0 - rgb.b) * (1.0 - warmFilter.b))));
	return clamp(mix(rgb, processed, temperature),0.0,1.0);
}

vec3 do_tint (vec3 incolor){
  if(abs(tint) < 0.001){
    return incolor;
  }
  vec3 color = incolor;
  if(tint < 0.0) {
      float value = (1.0 + 0.5 / 2.0);
      color.g = color.g * value;
  } else {
      float value = (1.0 + 0.5 / 2.0);
      color.b = color.b * value;
      color.r = color.r * value;
  }
  return clamp(mix(incolor,color,abs(tint)),0.0,1.0);
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
  vec4 o_color = incolor;
	incolor.rgb = do_saturation(incolor.rgb);
	incolor.rgb = do_vibrance(incolor.rgb);
	incolor.rgb = do_temperature(incolor.rgb);
  incolor.rgb = do_tint(incolor.rgb);
  vec4 mask_color = texture2D(tex_mask,texf);
	gl_FragColor = vec4(mix(o_color.rgb,incolor.rgb,clamp(mask_color.a,0.0,1.0)), incolor.a);
}
