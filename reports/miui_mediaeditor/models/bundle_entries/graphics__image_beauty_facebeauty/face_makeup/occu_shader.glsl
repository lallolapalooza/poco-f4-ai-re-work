
void main()
{
	//vec4 origin_c = texture2D(tex_origin, stf);
	//vec4 current_c = texture2D(tex, stf);
	vec2 p_origin = vec2(stf.x * width - width/ 2.0, stf.y * height - height / 2.0);
	vec2 p_rot;
	p_rot.x = cosAngle * p_origin.x - sinAngle * p_origin.y;
	p_rot.y = sinAngle * p_origin.x + cosAngle * p_origin.y;
	p_rot.x = p_rot.x + width_rot / 2.0;
	p_rot.y = p_rot.y + height_rot / 2.0;

	vec2 p_flip;
	p_flip.x = flip_x * (p_rot.x - width_rot / 2.0) + width_rot / 2.0;
	p_flip.y = flip_y * (p_rot.y - height_rot / 2.0) + height_rot / 2.0;

	vec3 p = vec3(p_flip,1.0);
	vec2 p_inv;
	vec3 st0 = vec3(occumatrix_inv1[0], occumatrix_inv1[1], occumatrix_inv1[2]);
	vec3 st1 = vec3(occumatrix_inv2[0], occumatrix_inv2[1], occumatrix_inv2[2]);
	p_inv.x = dot(p,st0);
	p_inv.y = dot(p,st1);
	float occu_value = 1.0;
	if(p_inv.x>=0.0 && p_inv.x<=sub_width && p_inv.y>=0.0 && p_inv.y<=sub_height){
		p_inv.x = p_inv.x / sub_width;
		p_inv.y = p_inv.y / sub_height;
		vec2 uv_occu =  vec2(p_inv.x, p_inv.y);
		float occu0 = texture2D(tex_mouthoccu, uv_occu).r;
		float occu_blur = 1.0 - texture2D(tex_face_occu_blur, uv_occu).a;
		occu_value = clamp(occu0 + occu_blur,0.0,1.0);
	}
	//gl_FragColor = vec4(mix(current_c.rgb, origin_c.rgb, 1.0 - occu_value), 1.0);
	gl_FragColor = vec4(vec3(occu_value), 1.0);
}