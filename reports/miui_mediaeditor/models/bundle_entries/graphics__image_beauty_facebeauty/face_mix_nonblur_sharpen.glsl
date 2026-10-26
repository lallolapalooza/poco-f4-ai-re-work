//face_mix_nonblur_sharpen
vec4 shader_main_s_sharpen_mix(vec2 st, vec4 C)
{
	if(sharpen_s_sharpen_mix <0.001)
	{
		return C;
	}
	else
	{
		vec4 result_color = C;
		vec4 st_offset1 = vec4(widthOffset_s_sharpen_mix ,heightOffset_s_sharpen_mix ,-widthOffset_s_sharpen_mix ,-heightOffset_s_sharpen_mix ) * 0.5 + vec4(st,st);
		vec4 st_offset2 = vec4(-widthOffset_s_sharpen_mix ,heightOffset_s_sharpen_mix ,widthOffset_s_sharpen_mix ,-heightOffset_s_sharpen_mix ) * 0.5 + vec4(st,st);
		float sum = texture2D(tex, st_offset1.xy).g;
		sum += texture2D(tex, st_offset1.zw).g;
		sum += texture2D(tex, st_offset2.xy).g;
		sum += texture2D(tex, st_offset2.zw).g;
		sum = 0.25 * sum;
		float highPass = C.g - sum + 0.5;
		float flag = step(0.5, highPass);
		vec3 hv = (2.0 * highPass + result_color.rgb - 1.0);
		vec3 color = mix(max(vec3(0.0), hv), min(vec3(1.0), hv), flag);
		result_color.rgb = mix(result_color.rgb, color.rgb, sharpen_s_sharpen_mix);
		return result_color;
	}
}