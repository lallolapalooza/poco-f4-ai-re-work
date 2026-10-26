void main()
{
		vec4 color = texture2D(tex_mask,st);
		float offset_scale = 1.0;
		float color0 = texture2D(tex_mask, arrvec2_sampleDirs[0] * offset_scale + st).a;
		float color1 = texture2D(tex_mask, arrvec2_sampleDirs[1] * offset_scale + st).a;
		float color2 = texture2D(tex_mask, arrvec2_sampleDirs[2] * offset_scale + st).a;
		float color3 = texture2D(tex_mask, arrvec2_sampleDirs[3] * offset_scale + st).a;
    float a = color.a;
		
    a = mix(a * 0.5,a,step(0.05,color0));
    a = mix(a * 0.5,a,step(0.05,color1));
    a = mix(a * 0.5,a,step(0.05,color2));
    a = mix(a * 0.5,a,step(0.05,color3));
		
		gl_FragColor=vec4(color.rgb,a);
}