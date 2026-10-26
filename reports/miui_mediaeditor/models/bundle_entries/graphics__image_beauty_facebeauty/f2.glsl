void main() 
{
	//should be merged
	vec3 origin_color = texture2D(tex_src, st).rgb;
	vec3 blur_color = texture2D(tex_mean, st).rgb;
	vec3 diff_color = (origin_color- blur_color) * 7.071068;
	diff_color = diff_color * diff_color;
	diff_color = min(diff_color, 1.0);
	gl_FragColor = vec4((diff_color.r + diff_color.g + diff_color.b)*0.333333);
}