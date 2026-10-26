varying vec2 texc;
varying vec2 texf;

float gray(vec3 incolor) {
  return dot(incolor, vec3(0.298839, 0.586811, 0.11435));
}
vec3 do_denoise(vec3 in_color) {
  vec4 clarity_color = texture2D(tex_clarity, texf);
  vec4 denoise_color = texture2D(tex_denoise, texf);
  vec3 result_color = in_color;
  float gray_color = exp(-3.0 * gray(denoise_color.rgb));
  float luminance_denoise1 = max(luminance_denoise - pow(denoise_color.a, 0.5), 0.0);
  //luminance_denoise1 = luminance_denoise;
  vec3 color1 = mix(denoise_color.rgb, clarity_color.rgb, luminance_denoise1 * gray_color);
  vec3 color2 = mix(denoise_color.rgb, clarity_color.rgb, color_denoise * gray_color);
  result_color = mix(result_color, color2 + gray(result_color) - gray(color2), color_denoise);
  result_color = mix(result_color, result_color + gray(color1) - gray(result_color), luminance_denoise1);
  result_color = clamp(result_color,0.0,1.0);
  return result_color;
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
float BlendSoftLight(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}
vec3 do_clarity(vec3 incolor) {
  vec3 originalColor = incolor;
  vec3 clarity_color = texture2D(tex_clarity, texf).rgb;

  clarity_color = fromSRGBMatr * (clarity_color * clarity_color);
  vec3 originalColor_t = fromSRGBMatr * (incolor * incolor);
  clarity_color = (originalColor_t + (1.0 - clarity_color)) * 0.5;

  originalColor = fromSRGBMatr * invSRGBGamma(originalColor);
	originalColor = pow(originalColor,vec3(1.0 / 2.2));

  vec3 blendedColor = mix(vec3(0.5), clarity_color, clarity);
  originalColor = vec3(BlendSoftLight(originalColor.r,blendedColor.r),BlendSoftLight(originalColor.g,blendedColor.g),BlendSoftLight(originalColor.b,blendedColor.b));
  originalColor = clamp(originalColor,0.0,1.0);

  originalColor = pow(originalColor,vec3(2.2));
  originalColor = sRGBGamma(toSRGBMatr * originalColor);
  return originalColor;
}

vec3 do_sharpen(vec3 in_color) {
	// vec2 step = 1.0 / vec2(input_w,input_h);
	// vec3 texA = texture2D( tex_input, texc + vec2(-step.x, -step.y) * 1.5 ).rgb;
	// vec3 texB = texture2D( tex_input, texc + vec2( step.x, -step.y) * 1.5 ).rgb;
	// vec3 texC = texture2D( tex_input, texc + vec2(-step.x,  step.y) * 1.5 ).rgb;
	// vec3 texD = texture2D( tex_input, texc + vec2( step.x,  step.y) * 1.5 ).rgb;
		
	// vec3 around = 0.25 * (texA + texB + texC + texD);
  vec3 around = texture2D(tex_sharpen, texf).rgb;
	vec3 col = in_color + (in_color - around) * sharpen;
  col = clamp(col,0.0,1.0);
	return col;
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
	vec4 o_color = incolor;
  incolor.rgb = do_clarity(incolor.rgb);
	incolor.rgb = do_denoise(incolor.rgb);
  incolor.rgb = do_sharpen(incolor.rgb);
	vec4 mask_color = texture2D(tex_mask,texf);
	gl_FragColor = vec4(mix(o_color.rgb,incolor.rgb,clamp(mask_color.a,0.0,1.0)), incolor.a);
}
