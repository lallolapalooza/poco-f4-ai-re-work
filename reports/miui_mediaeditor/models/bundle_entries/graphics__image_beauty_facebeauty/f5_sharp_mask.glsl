varying vec4 st_offset1;
varying vec4 st_offset2;
void main() 
{
	vec4 origin_color = texture2D(tex_src, st);
	vec4 blur_color = texture2D(tex_mean, st);
	vec4 diff_blur_color = texture2D(tex_diff, st);

	float skin_mask = texture2D(tex_mask, st).a;
	
	float value = clamp((min(origin_color.r, blur_color.r - 0.1) - 0.2) * 4.0, 0.0, 1.0);
	float averageHP = diff_blur_color.r;
	float currentIntensity = (1.0 - averageHP / (averageHP + 0.1)) * skin_mask;
	vec3 resultColor = mix(origin_color.rgb, blur_color.rgb, currentIntensity);

	gl_FragColor = vec4(resultColor.rgb, origin_color.a);
}