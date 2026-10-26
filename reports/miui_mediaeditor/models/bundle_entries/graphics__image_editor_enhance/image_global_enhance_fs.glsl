varying vec2 texc;
varying vec2 texf;

precision highp float;
vec4 lut_color (vec4 C,sampler2D tex_lut){
	float color_scale_s_color_custom_shader_whitten = 1.0;
	float raw_s_color_custom_shader_whitten = 8.0;
	float col_s_color_custom_shader_whitten = 8.0;
	float dim_s_color_custom_shader_whitten = 64.0;
	vec4 textureColor = C;
	textureColor = clamp(textureColor,0.01,0.99);
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
	vec4 newColor1 = texture2D(tex_lut  , texPos1,-15.0);
	vec4 newColor2 = texture2D(tex_lut  , texPos2,-15.0);
	vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
	return vec4(newColor1.rgb,C.a);
}

vec4 caculLut(vec4 inColor,sampler2D tex_lut){
    float g = inColor.g * 16.0;
    float v = (0.5 / 17.0) + inColor.r * (1.0 - 1.0 / 17.0);
    float b = inColor.b * 16.0 / 17.0 + (0.5 / 17.0);
    float u1 = b / 17.0 + floor(g) / 17.0;
    float u2 = b / 17.0 + ceil(g) / 17.0;
    vec4 newColor1 = texture2D(tex_lut, vec2(u1, v));
    vec4 newColor2 = texture2D(tex_lut, vec2(u2, v));
    return mix(newColor1, newColor2, fract(g));
}

vec3 fadeGray(vec3 src, float f)
{
	src.rgb = src.rgb - (src.rgb - 0.5) * f;
	return src;
}
vec3 fadeGray2(vec3 src, float f)
{
	float r = f + (1.0 - f) * src.r;
	float g = f + (1.0 - f) * src.g;
	float b = f + (1.0 - f) * src.b;
	return vec3(r, g, b);
}
vec3 fadeGray3(vec3 src, float f)
{
	vec3 src1 = src * 255.0;
	vec3 fade_color = 0.0024729083552612963*src1*src1+0.079212290976997*src1+74.0;
	return mix(src.rgb, fade_color / 255.0, f);
}

vec3 do_fade(vec3 in_color) {
	return clamp(fadeGray3(in_color, fade),0.0,1.0);
}

float BlendSoftLightf(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}
vec3 softLight(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res = vec3(BlendSoftLightf(origin.r, target.r),BlendSoftLightf(origin.g, target.g),BlendSoftLightf(origin.b, target.b));
	C_res = origin * (1.0-alpha) + C_res*alpha;
	return C_res;
}

vec3 do_grain(vec3 in_color) {
	vec3 grain_color = texture2D(tex_grain, texf).rgb;

	vec3 result = softLight(in_color.rgb,grain_color,1.0);
	return clamp(mix(in_color, result, grain),0.0,1.0);
}

vec3 do_halation(vec3 in_color) {
	float d = distance(texf, vec2(0.5));
	float VignetteX = 0.8, VignetteY = 0.25;
	float alpha = smoothstep(VignetteX, VignetteY, d);
	vec3 halation_color = vec3(1.0);
	if(halation < 0.0){
		halation_color = vec3(0.0);
	}
	vec3 vig = mix(halation_color, in_color.rgb, alpha);
	return clamp(mix(in_color.rgb, vig, abs(halation)),0.0,1.0);
}

vec4 do_light_perecption(vec4 incolor){
	vec4 p_color = lut_color(incolor,tex_plut);
	vec4 n_color = lut_color(incolor,tex_nlut);
	vec4 result_color = mix(n_color,p_color,step(0.0,light_perecption));
	float rate = 1.0;
	if(light_perecption < 0.0){
		rate = 1.2;
	}
	return clamp(mix(incolor,result_color,abs(light_perecption) * rate),0.0,1.0);
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
vec3 do_dehaze(vec3 incolor) {
	vec3 result_color = incolor;
	result_color = fromSRGBMatr * invSRGBGamma(result_color);
	result_color = pow(result_color,vec3(1.0 / 2.2));

	float dehaze_color = mix(1.0, max(0.01, 1.0 - texture2D(tex_dehaze,texf).a), dehaze);
	result_color = clamp((result_color - 0.95) / dehaze_color + 0.95,0.0,1.0);

	result_color = pow(result_color,vec3(2.2));
  	result_color = sRGBGamma(toSRGBMatr * result_color);
	return result_color;
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
	incolor.rgb = do_fade(incolor.rgb);
	incolor.rgb = do_grain(incolor.rgb);
	incolor.rgb = do_halation(incolor.rgb);
	incolor = do_light_perecption(incolor);
	incolor.rgb = do_dehaze(incolor.rgb);
	gl_FragColor = incolor;
}
