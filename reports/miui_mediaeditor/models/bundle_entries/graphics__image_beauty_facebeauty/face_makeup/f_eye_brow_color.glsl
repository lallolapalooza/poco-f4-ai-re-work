#ifdef OPENG_ES
precision highp float;
#endif
void main()
{
		vec2 st = stf;
		vec4 C = texture2D(tex,st);
		vec4 color1 = texture2D(tex_makeup,uv);
		vec4 color2 = vec4(0.0);
		vec4 color3 = vec4(0.0);
		if(makeup_color2.a > 0.001)
		{
			color2 = texture2D(tex_makeup2,uv);
		}
		if(makeup_color3.a > 0.001)
		{
			color3 = texture2D(tex_makeup3,uv);
		}
		if(reverse_alpha > 0.5)
		{
			if(update1 > 0.5)
			{
				color1.rgb = color1.rgb / color1.a;
				color1.rgb = clamp(color1.rgb,0.0,1.0);
			}
			if(update2 > 0.5)
			{
				color2.rgb = color2.rgb / color2.a;
				color2.rgb = clamp(color2.rgb,0.0,1.0);
			}
			if(update3 > 0.5)
			{
				color3.rgb = color3.rgb / color3.a;
				color3.rgb = clamp(color3.rgb,0.0,1.0);
			}
		}
		float factor1 = color1.a * makeup_intensity * makeup_color1.a; 
		float factor2 = color2.a * makeup_intensity * makeup_color2.a;
		float factor3 = color3.a * makeup_intensity * makeup_color3.a; 
		
		vec3 out_color = mix(C.rgb, makeup_color1.rgb, factor1);
		out_color = mix(out_color.rgb, makeup_color2.rgb, factor2);
		out_color = mix(out_color.rgb, makeup_color3.rgb, factor3);
		gl_FragColor = vec4(out_color.rgb, 1.0); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);
}