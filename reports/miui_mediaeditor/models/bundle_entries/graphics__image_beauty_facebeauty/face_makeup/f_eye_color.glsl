#ifdef OPENG_ES
precision highp float;
#endif
vec3 multiple(vec3 origin,vec3 target,float alpha)//正片叠底
{
	vec3 C_res=origin*(1.0-alpha)+origin*target*alpha;
	return C_res;
}
vec3 blend(vec3 origin,vec3 target,float alpha)//混合
{
	vec3 C_res=origin*(1.0-alpha)+target*alpha;
	return C_res;
}
vec3 add(vec3 origin,vec3 target,float alpha)//线性叠加
{
	vec3 C_res=min(origin + target*alpha, vec3(1.0));
	return C_res;
}
/* float BlendSoftLightf(float base, float blend) {
    return ((1.0 - 2.0 * (blend))*(base)*(base) + 2.0 * (base) * (blend));
}
vec3 softLight(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res = vec3(BlendSoftLightf(origin.r, target.r ),BlendSoftLightf(origin.g, target.g ),BlendSoftLightf(origin.b, target.b ));
	C_res = origin * (1.0-alpha) + C_res*alpha;
	return C_res;
} */
float BlendSoftLightf(float base, float blend) {
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend);
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}
vec3 softLight(vec3 origin,vec3 target,float alpha)//柔光
{
	vec3 C_res = vec3(BlendSoftLightf(origin.r, target.r ),BlendSoftLightf(origin.g, target.g ),BlendSoftLightf(origin.b, target.b ));
	C_res = origin * (1.0-alpha) + C_res*alpha;
	return C_res;
}
float BlendStrongLightf(float base, float blend) {
  float color1 = 2.0 * base * blend;
  float color2 = 1.0 - 2.0 * (1.0 - base) * (1.0 - blend);
  return mix(color1, color2, step(0.5, blend));
}
vec3 strongLight(vec3 origin,vec3 target,float alpha)//强光
{
	vec3 C_res = vec3(BlendStrongLightf(origin.r, target.r ),BlendStrongLightf(origin.g, target.g ),BlendStrongLightf(origin.b, target.b ));
	C_res = origin * (1.0-alpha) + C_res*alpha;
	return C_res;
}

vec3 blend_all(vec3 origin,vec3 target,float alpha,float type)
{
	vec3 C_res;
	if(type < 0.5)
		C_res = multiple(origin,target,alpha);
	else if(type < 1.5)
		C_res = blend(origin,target,alpha);
	else if(type < 2.5)
		C_res = add(origin,target,alpha);
	else if(type < 3.5)
		C_res = softLight(origin,target,alpha);
	else if(type < 4.5)
		C_res = strongLight(origin,target,alpha);
	return C_res;
}
void main()
{
		vec2 st = stf;
		vec4 C = texture2D(tex,st);
		vec4 color1 = vec4(0.0);
		vec4 color12 = vec4(0.0);
		vec4 color13 = vec4(0.0);
		vec4 color14 = vec4(0.0);
		vec4 color2 = vec4(0.0);
		vec4 color3 = vec4(0.0);
		if( makeup_intensity1 > 0.001)
		{
			
			if(makeup11 > 0.5)
			{
				color1 = texture2D(tex_makeup1,uv);
			}
			if(makeup12 > 0.5)
			{
				color12 = texture2D(tex_makeup12,uv);
			}
			if(makeup13 > 0.5)
			{
				color13 = texture2D(tex_makeup13,uv);
			}
			if(makeup14 > 0.5)
			{
				color14 = texture2D(tex_makeup14,uv);
			}
		}
		if( makeup_intensity2 > 0.001)
		{
			color2 = texture2D(tex_makeup2,uv);
		}
		if( makeup_intensity3 > 0.001)
		{
			color3 = texture2D(tex_makeup3,uv);
		}
		if(reverse_alpha > 0.5)
		{
			if(update1 > 0.5 && makeup_intensity1 > 0.001)
			{
				color1.rgb = color1.rgb / color1.a;
				color1.rgb = clamp(color1.rgb,0.0,1.0);
			}
			if(update12 > 0.5 && makeup_intensity1 > 0.001)
			{
				color12.rgb = color12.rgb / color12.a;
				color12.rgb = clamp(color12.rgb,0.0,1.0);
			}
			if(update13 > 0.5 && makeup_intensity1 > 0.001)
			{
				color13.rgb = color13.rgb / color13.a;
				color13.rgb = clamp(color13.rgb,0.0,1.0);
			}
			if(update14 > 0.5 && makeup_intensity1 > 0.001)
			{
				color14.rgb = color14.rgb / color14.a;
				color14.rgb = clamp(color14.rgb,0.0,1.0);
			}
			if(update2 > 0.5&& makeup_intensity2 > 0.001)
			{
				color2.rgb = color2.rgb / color2.a;
				color2.rgb = clamp(color2.rgb,0.0,1.0);
			}
			if(update3 > 0.5&& makeup_intensity3 > 0.001)
			{
				color3.rgb = color3.rgb / color3.a;
				color3.rgb = clamp(color3.rgb,0.0,1.0);
			}
		}
		float factor1 = color1.a * makeup_intensity1 ;
		// float factor12 = color12.a * makeup_intensity1*makeup_color12.a ;
		// float factor13 = color13.a * makeup_intensity1*makeup_color13.a ;
		// float factor14 = color14.a * makeup_intensity1*makeup_color14.a ;
		float factor12 = color12.a * makeup_intensity1;
		float factor13 = color13.a * makeup_intensity1;
		float factor14 = color14.a * makeup_intensity1;
		float factor2 = color2.a * makeup_intensity2;
		float factor3 = color3.a * makeup_intensity3 ; 
		if(makeup_color1.a > 0.001)
		{
			factor1 *=  makeup_color1.a;
			color1.rgb  = makeup_color1.rgb;
		}
		if(makeup_color12.a > 0.001)
		{
			//factor12 *=  makeup_color12.a;
			factor12 *=  makeup_color12.a;
			color12.rgb  = makeup_color12.rgb;
		}
		if(makeup_color13.a > 0.001)
		{
			//factor13 *=  makeup_color13.a;
			factor13 *=  makeup_color13.a;
			color13.rgb  = makeup_color13.rgb;
		}
		if(makeup_color14.a > 0.001)
		{
			//factor14 *=  makeup_color14.a;
			factor14 *=  makeup_color14.a;
			color14.rgb  = makeup_color14.rgb;
		}
		if(makeup_color2.a > 0.001)
		{
			factor2 *=  makeup_color2.a;
			color2.rgb  = makeup_color2.rgb;
		}
		if(makeup_color3.a > 0.001)
		{
			factor3 *=  makeup_color3.a;
			color3.rgb  = makeup_color3.rgb;
		}
		// vec3 out_color = mix(C.rgb, color1.rgb, factor1);
		// out_color = mix(out_color.rgb, color12.rgb, factor12);
		// out_color = mix(out_color.rgb, color13.rgb, factor13);
		// out_color = mix(out_color.rgb, color2.rgb, factor2);
		// out_color = mix(out_color.rgb, color3.rgb, factor3);
		
		vec3 out_color = C.rgb;
		out_color = blend_all(out_color,color1.rgb,factor1,blend_type_tex_eye);
		
		out_color = blend_all(out_color,color12.rgb,factor12,blend_type_tex_eye2);
		
		out_color = blend_all(out_color,color13.rgb,factor13,blend_type_tex_eye3);
		
		out_color = blend_all(out_color,color14.rgb,factor14,blend_type_tex_eye4);
		
		
		out_color = blend_all(out_color,color2.rgb,factor2,blend_type_tex_eyeLiner);
		out_color = blend_all(out_color,color3.rgb,factor3,blend_type_tex_eyeLash);

		
		//gl_FragColor = vec4(out_color.rgb, 1.0); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);

	//gl_FragColor = vec4(mix(out_color, C.rgb, 1.0 - occu_value), 1.0);
	gl_FragColor = vec4(out_color.rgb, 1.0); 
}