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

vec2 calcNewUVRight() {
	if(right_intensity < 0.5){
		return vec2(0.0, 0.0);
	}
	vec2 coor = stf;
	float d_y = abs(right_k1 * coor.x - coor.y + right_b1) / sqrt(1.0 + right_k1 * right_k1);
	float d_y1 = abs(right_k1 * coor.x - coor.y + right_b1_1) / sqrt(1.0 + right_k1 * right_k1);
	float d_y2 = abs(right_k1 * coor.x - coor.y + right_b1_2) / sqrt(1.0 + right_k1 * right_k1);
	float d_x = abs(right_k2 * coor.x - coor.y + right_b2) / sqrt(1.0 + right_k2 * right_k2);
	float d_x1 = abs(right_k2 * coor.x - coor.y + right_b2_1) / sqrt(1.0 + right_k2 * right_k2);
	float d_x2 = abs(right_k2 * coor.x - coor.y + right_b2_2) / sqrt(1.0 + right_k2 * right_k2);

	vec2 tex_cur = vec2(0.0);
	if (d_y + d_y2 <= right_rect_h_u && d_x + d_x2 <= right_rect_w_l) {
		vec2 temp = vec2(d_x2, d_y2) / vec2(right_rect_w_l / 1.1, right_rect_h_u / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 + temp.x * 0.5, 0.5 + temp.y * 0.5);
	} else if (d_y + d_y2 <= right_rect_h_u && d_x2 + d_x1 <= right_rect_w_r) {
		vec2 temp = vec2(d_x2, d_y2) / vec2(right_rect_w_r / 1.1, right_rect_h_u / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 - temp.x * 0.5, 0.5 + temp.y * 0.5);
	} else if (d_y2 + d_y1 <= right_rect_h_d && d_x2 + d_x1 <= right_rect_w_r) {
		vec2 temp = vec2(d_x2, d_y2) / vec2(right_rect_w_r / 1.1, right_rect_h_d / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 - temp.x * 0.5, 0.5 - temp.y * 0.5);
	} else if (d_y2 + d_y1 <= right_rect_h_d && d_x + d_x2 <= right_rect_w_l) {
		vec2 temp = vec2(d_x2, d_y2) / vec2(right_rect_w_l / 1.1, right_rect_h_d / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 + temp.x * 0.5, 0.5 - temp.y * 0.5);
	}else{
		return vec2(0.0, 0.0);
	}
	if(flip > 0.5){
		return vec2(tex_cur.x,1.0 - tex_cur.y);
	}else{
		return vec2(1.0 - tex_cur.x,1.0 - tex_cur.y);
	}
	return vec2(0.0, 0.0);
}

vec2 calcNewUVLeft() {
	if(left_intensity < 0.5){
		return vec2(0.0, 0.0);
	}
	vec2 coor = stf;
	float d_y = abs(left_k1 * coor.x - coor.y + left_b1) / sqrt(1.0 + left_k1 * left_k1);
	float d_y1 = abs(left_k1 * coor.x - coor.y + left_b1_1) / sqrt(1.0 + left_k1 * left_k1);
	float d_y2 = abs(left_k1 * coor.x - coor.y + left_b1_2) / sqrt(1.0 + left_k1 * left_k1);
	float d_x = abs(left_k2 * coor.x - coor.y + left_b2) / sqrt(1.0 + left_k2 * left_k2);
	float d_x1 = abs(left_k2 * coor.x - coor.y + left_b2_1) / sqrt(1.0 + left_k2 * left_k2);
	float d_x2 = abs(left_k2 * coor.x - coor.y + left_b2_2) / sqrt(1.0 + left_k2 * left_k2);

	vec2 tex_cur = vec2(0.0);
	if(d_y + d_y2 <= left_rect_h_u && d_x + d_x2 <= left_rect_w_l){
		vec2 temp = vec2(d_x2, d_y2) / vec2(left_rect_w_l / 1.1,left_rect_h_u / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 + temp.x * 0.5,0.5 + temp.y * 0.5);
	}else if(d_y + d_y2 <= left_rect_h_u && d_x2 + d_x1 <= left_rect_w_r){
		vec2 temp = vec2(d_x2, d_y2) / vec2(left_rect_w_r / 1.1,left_rect_h_u / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 - temp.x * 0.5,0.5 + temp.y * 0.5);
	}else if(d_y2 + d_y1 <= left_rect_h_d && d_x2 + d_x1 <= left_rect_w_r){
		vec2 temp = vec2(d_x2, d_y2) / vec2(left_rect_w_r / 1.1,left_rect_h_d / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 - temp.x * 0.5,0.5 - temp.y * 0.5);
	}else if(d_y2 + d_y1 <= left_rect_h_d && d_x + d_x2 <= left_rect_w_l){
		vec2 temp = vec2(d_x2, d_y2) / vec2(left_rect_w_l / 1.1,left_rect_h_d / 1.1);
		temp = clamp(temp,0.0,1.0);
		tex_cur = vec2(0.5 + temp.x * 0.5,0.5 - temp.y * 0.5);
	}else{
		return vec2(0.0, 0.0);
	}
	if(flip > 0.5){
		return vec2(tex_cur.x,1.0 - tex_cur.y);
	}else{
		return vec2(1.0 - tex_cur.x,1.0 - tex_cur.y);
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