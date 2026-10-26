#ifdef OPENG_ES
precision highp float;
#endif
void main()
{
		vec2 st = stf;
		vec4 C = vec4(0.0);
		vec4 C1 = texture2D(tex_makeup,uv);
		if(reverse_alpha > 0.5)
		{
			C1.rgb = C1.rgb / C1.a;
			C1.rgb = clamp(C1.rgb,0.0,1.0);
		}
		float factor = C1.a * makeup_intensity; 
	 
		gl_FragColor = vec4(C1.rgb, factor); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);
}