#ifdef OPENG_ES
precision highp float;
#endif
float BlendOverlayf(float base, float blend)
{return (base<0.5?(2.0*(base)*(blend)):(1.0-2.0*(1.0-(base))*(1.0-(blend))));}
void main()
{
		float factorThreshold = 0.509;
		vec4 C1 = texture2D(tex_makeup,uv);
		if(reverse_alpha > 0.5)
		{
			C1.rgb = C1.rgb / C1.a;
			C1.rgb = clamp(C1.rgb,0.0,1.0);
		}
		vec4 C2 = texture2D(tex_highlight,uv);
		if(reverse_alpha > 0.5)
		{
			C2.rgb = C2.rgb / C2.a;
			C2.rgb = clamp(C2.rgb,0.0,1.0);
		}
		//vec4 mouthColor=vec4(C1.rgb,makeup_intensity *C1.a );
		vec4 mouthColor=vec4(lip_color.rgb, lip_color.a * makeup_intensity *C1.r);
		//vec4 mouthColor=vec4(0.72,0.078,0.21,0.86 * makeup_intensity *C1.a );
		//vec4 mouthColor=vec4(128.0/255.0,64.0 / 255.0,6.0 / 255.0,0.714286* makeup_intensity *C1.a );
		vec3 source = texture2D(tex,stf).rgb;
		float contrast = 0.5;
		float gray=0.299*source.r+0.587*source.g+0.114*source.b;
		float alpha = mouthColor.a ;
		float blend_weight=texture2D(tex_lut,vec2(gray,0)).r,level_weight=texture2D(tex_lut2,vec2(gray,0)).r*alpha;
		vec3 color=mix(mouthColor.rgb*source,mouthColor.rgb,blend_weight);
		vec3 target_color=mix(source,color,level_weight);
		float diff=max(gray-factorThreshold,0.0)*alpha;
		 target_color=vec3(1.0)-(vec3(1.0)-target_color)*vec3(1.0-diff);
		 if(mouthColor.a>0.001)
		{
			target_color.r=BlendOverlayf(target_color.r,contrast);
			target_color.g=BlendOverlayf(target_color.g,contrast);
			target_color.b=BlendOverlayf(target_color.b,contrast);
		}
		if(highlight > 0.5)
			target_color = mix(target_color,C2.rgb,C2.a * alpha );
		gl_FragColor=vec4(target_color,1.0);
		
}