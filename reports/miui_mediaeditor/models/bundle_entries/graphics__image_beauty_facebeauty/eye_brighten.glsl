vec4 shader_main_s_eye_brighten_shader(vec2 st, vec4 C)
{
	//return C;
	
     vec2 curcood = st * dims_s_eye_brighten_shader;
	
	if(sharpness_s_eye_brighten_shader > 0.001)
	{
	vec2 stride = vec2(sizeInvW_s_eye_brighten_shader,sizeInvH_s_eye_brighten_shader)*image_ratio_to_std_size_s_eye_brighten_shader;
	vec4 pupu=vec4(sizeInvW_s_eye_brighten_shader,sizeInvH_s_eye_brighten_shader,sizeInvW_s_eye_brighten_shader,sizeInvH_s_eye_brighten_shader)*image_ratio_to_std_size_s_eye_brighten_shader;
	vec4 stst=vec4(st,st);
	vec4 bc0_bc1 = stst + vec4(vec2(0.0,-8.0) , vec2(-8.0,0.0)) * pupu ;
	vec4 bc2_bc3 = stst + vec4(vec2(8.0,0.0) , vec2(0.0,8.0)) * pupu ;
	float radius = standardLength_s_eye_brighten_shader*0.8;//0.35
	float intensity = sharpness_s_eye_brighten_shader;
	vec2 aspect = abs(p_eyea_s_eye_brighten_shader.x-p_eyeb_s_eye_brighten_shader.x) > abs(p_eyea_s_eye_brighten_shader.y-p_eyeb_s_eye_brighten_shader.y) ? vec2(0.5625,1.0):vec2(1.0,0.5625);
	vec2 curcood_aspect = curcood *aspect;
	vec2 p_eyea_aspect = p_eyea_s_eye_brighten_shader * aspect;
	vec2 p_eyeb_aspect = p_eyeb_s_eye_brighten_shader * aspect;
	vec2 dist = curcood_aspect - p_eyea_aspect;
	vec2 dist2 = curcood_aspect - p_eyeb_aspect;

	vec3 maskrgb =texture2D(tex_mask,st).rgb;
	float mask = 0.0;
	if( maskrgb.g > 0.0 && maskrgb.r <= 0.1 && maskrgb.b <= 0.1){
		mask = maskrgb.g;
	}

#ifdef DEBUG_MASK
	return vec4(mix(C.rgb,vec3(0.0,1.0,0.0),mask*mask_intensity_s_eye_brighten_shader),C.a);	
#endif

	vec3 res = C.rgb;
	if(mask > 0.01)
	{
		intensity *= mask;
		float dis1 = distance(curcood,p_eyea_s_eye_brighten_shader);
		float dis2 = distance(curcood,p_eyeb_s_eye_brighten_shader);
		if(dis1 < standardLength_s_eye_brighten_shader*0.4) intensity = intensity*left_intensity_s_eye_brighten_shader;
		else if(dis2 < standardLength_s_eye_brighten_shader*0.4) intensity = intensity*right_intensity_s_eye_brighten_shader;
		vec4 origin = texture2D(tex, st.xy);
		vec4 resultColor = origin;
		vec2 step1 = vec2(sizeInvW_s_eye_brighten_shader, 0.0)*image_ratio_to_std_size_s_eye_brighten_shader;
		vec2 step2 = vec2(0.0, sizeInvH_s_eye_brighten_shader)*image_ratio_to_std_size_s_eye_brighten_shader;
		vec3 sumColor = origin.rgb;
			
		sumColor += texture2D(tex,st + (-2.0) * step1).rgb;
		sumColor += texture2D(tex,st +  2.0   * step1).rgb;
		sumColor += texture2D(tex,st + (-2.0) * step2).rgb;
		sumColor += texture2D(tex,st +  2.0   * step2).rgb;
		
		sumColor += texture2D(tex,st + step1).rgb;
		sumColor += texture2D(tex,st - step1).rgb;
		sumColor += texture2D(tex,st + step2).rgb;
		sumColor += texture2D(tex,st - step2).rgb;
		
		sumColor += texture2D(tex,st + -step1 +  step2).rgb;
		sumColor += texture2D(tex,st + -step1 + -step2).rgb;
		sumColor += texture2D(tex,st +  step1 +  step2).rgb;
		sumColor += texture2D(tex,st +  step1 + -step2).rgb;
		sumColor = sumColor * 0.07692;
		vec3 stepSumColor = step(0.0,(origin.rgb - sumColor).rgb);
		vec3 stepSumColor2 = clamp(5.0 * (origin.g + 0.7) * (origin.rgb - sumColor.rgb + 0.1),0.0,1.0);
		stepSumColor2 = mix(stepSumColor2,vec3(1.0,1.0,1.0),stepSumColor);
		sumColor = clamp(C.rgb + (origin.rgb - sumColor) , 0.0, 1.0);
		sumColor = max(C.rgb - 0.05, sumColor);
		vec3 sumColorBright = sumColor;
		sumColorBright.r = texture2D(tex_curve_s_eye_brighten_shader, vec2(sumColor.r, 0.5)).r;
		sumColorBright.g = texture2D(tex_curve_s_eye_brighten_shader, vec2(sumColor.g, 0.5)).g;
		sumColorBright.b = texture2D(tex_curve_s_eye_brighten_shader, vec2(sumColor.b, 0.5)).b;
		sumColorBright = mix(sumColor, sumColorBright, 1.0 * stepSumColor2);
		res = mix(C.rgb, sumColorBright, intensity*1.5);
	}
	return vec4(res,1.0);
	}
	else
	{
		return C;
	}
	
	
}