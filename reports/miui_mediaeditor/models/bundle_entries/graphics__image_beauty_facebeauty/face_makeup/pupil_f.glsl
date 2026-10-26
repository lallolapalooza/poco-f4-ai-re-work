#ifdef OPENG_ES
precision highp float;
#endif
float cross_calc_eye(vec2 a,vec2 b)
{
	return a.x*b.y - b.x*a.y;
}
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

vec2 calcNewUVRight() {
	vec2 coor = gl_FragCoord.xy / vec2(screensize[0], screensize[1]) * 2.0 - 1.0;
	vec2 ret = vec2(0.0, 0.0);

	vec2 opr = vec2(coor.x - right_eye0.x, coor.y - right_eye0.y);
	vec2 oar = vec2(right_eye1.x - right_eye0.x, right_eye1.y - right_eye0.y);
	vec2 obr = vec2(right_eye2.x - right_eye0.x, right_eye2.y - right_eye0.y);

	float cr = opr.x;
	float fr = opr.y;
	float ar = oar.x;
	float dr = oar.y;
	float br = obr.x;
	float er = obr.y;

	float vr = ((fr - dr * cr / ar) / (er - dr * br / ar));
	float ur = ((cr - br * vr) / ar);

	//return vec2(vr, ur);
	if (0.0 < ur && ur <= 1.0 && 0.0 < vr && vr <= 1.0) {
		if (flip > 0.5) {
			return vec2(1.0 - vr, 1.0 - ur);
		} else {
			return vec2(vr, 1.0 - ur);
		}
	}
	return vec2(0.0, 0.0);
}

vec2 calcNewUVLeft() {
	vec2 coor = gl_FragCoord.xy / vec2(screensize[0], screensize[1]) * 2.0 - 1.0;
	vec2 ret = vec2(0.0, 0.0);

	vec2 opl = vec2(coor.x - left_eye0.x, coor.y - left_eye0.y);
	vec2 oal = vec2(left_eye1.x - left_eye0.x, left_eye1.y - left_eye0.y);
	vec2 obl = vec2(left_eye2.x - left_eye0.x, left_eye2.y - left_eye0.y);

	float cl = opl.x;
	float fl = opl.y;
	float al = oal.x;
	float dl = oal.y;
	float bl = obl.x;
	float el = obl.y;

	float vl = (fl - dl * cl / al) / (el - dl * bl / al);
	float ul = (cl - bl * vl) / al;


	if (0.0 < ul && ul <= 1.0 && 0.0 < vl && vl <= 1.0) {
		if (flip > 0.5) {
			return vec2(1.0 - vl,  1.0 - ul);
		} else {
			return vec2(vl,  1.0 - ul);
		}
	}
	return vec2(0.0, 0.0);
}

void main()
{
		vec2 st = stf;
		vec4 C = texture2D(tex,st);
		vec4 C1 = texture2D(tex_makeup, calcNewUVLeft() + calcNewUVRight());
		float mask = texture2D(tex_eye_mask, uv).a;
		// vec2 t = calcNewUV();
		// if (t.r > 0.0) {
		// 	gl_FragColor = vec4(t, 0.0, 1.0);
		// } else {
		// 	gl_FragColor = C;
		// }
		// return ;
		//gl_FragColor = vec4(calcNewUVLeft() + calcNewUVRight(), 0.0, 1.0); 
		//return;
		if (mask < 0.01) {
			gl_FragColor = C;
			return;
		}
		//gl_FragColor = vec4(calcNewUV(), 0.0, 1.0);
		//return;
		if(reverse_alpha > 0.5)
		{
			C1.rgb = C1.rgb / C1.a;
			C1.rgb = clamp(C1.rgb,0.0,1.0);
		}
		float factor = C1.a * makeup_intensity; 
		
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
		//if (factor > 0.01) {
		//	gl_FragColor = vec4(color_out, factor); 
		//} else {
		//	discard;
		//}

	//gl_FragColor = vec4(mix(color_out, C.rgb, 1.0 - occu_value), factor);
	gl_FragColor = vec4(color_out, factor);
}