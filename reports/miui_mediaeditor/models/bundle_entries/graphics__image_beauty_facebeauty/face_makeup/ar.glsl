vec4 shader_main(vec4 C){

	vec4 C1 = C;
	if(reverse_alpha > 0.5 && C1.a > 0.001)
	{
		C1.rgb = C1.rgb / C1.a;
		C1.rgb = clamp(C1.rgb,0.0,1.0);
	}
	return vec4(C1.rgb ,C1.a * alpha);
}
