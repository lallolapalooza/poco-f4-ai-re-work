vec4 shader_main_s_surfaceblur_mix(vec2 st, vec4 C)
{
	if(blur_amount_s_clear_blur_mix<0.001)
	{
		return C;
	}
	else
	{
		vec3 blur1 = texture2D(tex_surfaceblur1, st).rgb;
		vec3 blur2 = texture2D(tex_surfaceblur2, st).rgb;
		vec4 mask =texture2D(tex_mask,st);
		float eye_mask = 0.0;
		if(mask.g > 0.0 && mask.r <= 0.1 && mask.b <= 0.1){
			eye_mask = mask.g;
		}
		float skin_mask = mask.a;

		float intensity = blur_amount_s_clear_blur_mix;

#ifdef DEBUG_MASK
		return vec4(mix(C.rgb,vec3(1.0,0.0,1.0),intensity * skin_mask),C.a);
#endif

#ifdef USE_SKIN_DETECT
		if(skin_detect_s_clear_blur_mix > 0.5){
			float r = C.r;
			float g = C.g;
			float b = C.b;
			if(r > 0.371 && g >0.156 && b > 0.078 && r > b && r > g && abs(r-g)>0.059 && (max(max(r,g),b)-min(min(r,g),b) > 0.059)){
				//skin
			}else{
				intensity = nonskin_blur_scale_s_clear_blur_mix * intensity; //reduce blur
			}
		}
#endif

		intensity = mix(intensity*1.4286,1.0,step(0.7,intensity));
		float evenfactor = mix(0.3,intensity-0.3,step(0.6,intensity));
		vec3 diff = (C.rgb - blur1) * 0.5 + 0.5;
		vec3 even = clamp(2.0 * diff + blur2 - 1.0, vec3(0.0),vec3(1.0));
		even = mix(C.rgb,even,skin_mask);

		even = mix(even,blur2, skin_mask * evenfactor);
		vec3 res = mix(C.rgb,even,intensity);

		vec4 result_color = vec4(mix(C.rgb, res, (1.0 - eye_mask)),C.a);

#ifdef USE_SHARPEN
			vec4 st_offset1 = vec4(widthOffset_s_clear_blur_mix ,heightOffset_s_clear_blur_mix ,-widthOffset_s_clear_blur_mix ,-heightOffset_s_clear_blur_mix ) * 0.5 + vec4(st,st);
			vec4 st_offset2 = vec4(-widthOffset_s_clear_blur_mix ,heightOffset_s_clear_blur_mix ,widthOffset_s_clear_blur_mix ,-heightOffset_s_clear_blur_mix ) * 0.5 + vec4(st,st);
			float sum = texture2D(tex, st_offset1.xy).g;
			sum += texture2D(tex, st_offset1.zw).g;
			sum += texture2D(tex, st_offset2.xy).g;
			sum += texture2D(tex, st_offset2.zw).g;
			sum = 0.25 * sum;
			float highPass = C.g - sum + 0.5;
			float flag = step(0.5, highPass);
			vec3 hv = (2.0 * highPass + result_color.rgb - 1.0);
			vec3 color = mix(max(vec3(0.0), hv), min(vec3(1.0), hv), flag);
			result_color.rgb = mix(result_color.rgb, color.rgb, sharpen_s_clear_blur_mix );
#endif
		result_color.a = C.a;
		return result_color;
	}
}