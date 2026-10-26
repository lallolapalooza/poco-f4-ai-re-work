//meiyan3
vec4 shader_main_s_clear_blur_mix(vec2 st, vec4 C)
{
	if(blur_amount_s_clear_blur_mix<0.001)
	{
		return C;
	}
	else
	{
		vec4 beautified = texture2D(tex_beautified_s_clear_blur_mix, st);

		vec4 maskrgb=texture2D(tex_mask,st);
		float eye_mask = 0.0;
		if( maskrgb.g > 0.0 && maskrgb.r <= 0.01 && maskrgb.b <= 0.01){
			eye_mask = maskrgb.g;
		}

		float a = blur_amount_s_clear_blur_mix;
		if(skin_detect_s_clear_blur_mix > 0.5){
			float r = C.r;
			float g = C.g;
			float b = C.b;
			if(r > 0.371 && g >0.156 && b > 0.078 && r > b && r > g && abs(r-g)>0.059 && (max(max(r,g),b)-min(min(r,g),b) > 0.059)){
				//skin
			}else{
				a = nonskin_blur_scale_s_clear_blur_mix * a; //reduce blur
			}
		}
		vec4 result_color = mix(C, beautified, a * (1.0 - eye_mask));

#ifdef DEBUG_MASK
		return vec4(mix(result_color.rgb,vec3(1.0,0.0,1.0),blur_amount_s_clear_blur_mix*maskrgb.a),C.a);
#endif

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