#ifdef OPENG_ES
precision highp float;
#endif
float cross_calc_eye(vec2 a,vec2 b)
{
	return a.x*b.y - b.x*a.y;
}
vec3 multiple(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res=origin*(1.0-alpha)+origin*target*alpha;
	return C_res;
}
vec3 blend(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res=origin*(1.0-alpha)+target*alpha;
	return C_res;
}
vec3 add(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res=min(origin + target*alpha, vec3(1.0));
	return C_res;
}
float BlendSoftLightf(float base, float blend) {
    return ((1.0 - 2.0 * (blend))*(base)*(base) + 2.0 * (base) * (blend));
}
vec3 softLight(vec3 origin,vec3 target,float alpha)
{
	vec3 C_res = vec3(BlendSoftLightf(origin.r, target.r ),BlendSoftLightf(origin.g, target.g ),BlendSoftLightf(origin.b, target.b ));
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
	return C_res;
}
void main()
{
		vec2 st = stf;
		vec4 C = texture2D(tex,st);
		vec4 C1 = texture2D(tex_makeup,uv);
		float mask = texture2D(tex_eye_mask, st).a;
		if(reverse_alpha > 0.5)
		{
			C1.rgb = C1.rgb / C1.a;
			C1.rgb = clamp(C1.rgb,0.0,1.0);
		}
		float factor = C1.a * makeup_intensity; 
		//vec2 curcood = st * dims;
		// vec2 line1 = p31 - p72;
		// vec2 line2 = p72 - p32;
		// vec2 line3 = p32 - p69;
		// vec2 line4 = p69 - p33;
	 
		// vec2 line5 = p33 - p70;
		// vec2 line6 = p70 - p34;
		// vec2 line7 = p34 - p71;
		// vec2 line8 = p71 - p31;
		// vec2 dst_line1 = curcood - p72;
		// vec2 dst_line2 = curcood - p32;
		// vec2 dst_line3 = curcood - p69;
	    // vec2 dst_line4 = curcood - p33;
	    // vec2 dst_line5 = curcood - p70;
	    // vec2 dst_line6 = curcood - p34;
	    // vec2 dst_line7 = curcood - p71;
		// vec2 dst_line8 = curcood - p31;
		// if( cross_calc_eye(line1,dst_line1) < 0.0 && cross_calc_eye(line2,dst_line2) < 0.0 && cross_calc_eye(line3,dst_line3) < 0.0 && cross_calc_eye(line4,dst_line4) < 0.0 && cross_calc_eye(line5,dst_line5) < 0.0
		// &&cross_calc_eye(line6,dst_line6) < 0.0&&cross_calc_eye(line7,dst_line7) < 0.0&&cross_calc_eye(line8,dst_line8) < 0.0)
		// {
		// 	factor *= 1.0;
		// }
		// else
		// {
		// 	factor *= 0.0;
		// }
		factor *= mask;
		vec3 color_out;
		if(use_color > 0.5)
		{
			factor *= color.a;
			color_out =  color.rgb;
		}
		else
		{
			color_out =  C1.rgb;
		}
		color_out = blend_all(C.rgb,color_out,factor,blend_type_tex_pupil);
		gl_FragColor = vec4(color_out, factor); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);
}