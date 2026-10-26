#ifdef OPENG_ES
precision highp float;
#endif
varying vec2 stf_block;
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
		if (lar < -0.5 && leftIntensity < 0.5) {
				gl_FragColor =  C;
				return ;
		}
		if (lar > 0.5 && rightIntensity < 0.5) {
				gl_FragColor =  C;
				return ;
		}
		vec4 color1 = texture2D(tex_makeup,uv);
		if(reverse_alpha > 0.5)
		{
			if(update1 > 0.5)
			{
				color1.rgb = color1.rgb / color1.a;
				color1.rgb = clamp(color1.rgb,0.0,1.0);
			}
		}
		float factor1 = color1.a * makeup_intensity;
		vec3 color = color1.rgb;	
		if(makeup_color1.a > 0.001)
		{
			factor1 = color1.a * makeup_intensity * makeup_color1.a;
			color = makeup_color1.rgb;
		}
		color = blend_all(C.rgb,color,factor1,blend_type_tex_brown);
		//color = multiple(C.rgb,color,factor1);
		//gl_FragColor = vec4(color,1.0); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);


	//gl_FragColor = vec4(mix(color, C.rgb, 1.0 - occu_value), 1.0);
	//gl_FragColor = vec4(color,1.0); 
	float mask_color = 0.0;
	if (stf_block.x >= offset.x && stf_block.x <= offset.x + wh.x && stf_block.y >= offset.y &&
      	stf_block.y <= offset.y + wh.y) {
		vec2 makeup_texv = stf_block - offset;
		makeup_texv.x /= wh.x;
		makeup_texv.y /= wh.y;
		mask_color = texture2D(tex_makeup_mask,makeup_texv).r;
	}
	gl_FragColor = vec4(mix(C.rgb,color,mask_color),1.0);
}