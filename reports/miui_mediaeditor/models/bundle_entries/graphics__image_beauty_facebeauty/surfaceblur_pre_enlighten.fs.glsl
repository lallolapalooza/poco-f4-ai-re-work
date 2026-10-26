varying vec2 st;
vec4 shader_main_s_pre_enlighten(vec4 C,float blend_factor){
		vec4 textureColor = C;
		float blueColor = textureColor.b * (dim_s_pre_enlighten - 1.0);
		float r_raw = 1.0 / raw_s_pre_enlighten;
		float r_col = 1.0 / col_s_pre_enlighten;
		vec2 quad1;
		quad1.y = floor(floor(blueColor) / raw_s_pre_enlighten);
		quad1.x = floor(blueColor) - (quad1.y * col_s_pre_enlighten);
		vec2 quad2;
		quad2.y = floor(ceil(blueColor) / raw_s_pre_enlighten);
		quad2.x = ceil(blueColor) - (quad2.y * col_s_pre_enlighten);
		vec2 texPos1;
		texPos1.x = (quad1.x * r_col) + 0.5/(col_s_pre_enlighten * dim_s_pre_enlighten ) + ((r_col - 1.0/(col_s_pre_enlighten * dim_s_pre_enlighten )) * textureColor.r);
		texPos1.y = (quad1.y * r_raw) + 0.5/(raw_s_pre_enlighten * dim_s_pre_enlighten ) + ((r_raw - 1.0/(raw_s_pre_enlighten * dim_s_pre_enlighten )) * textureColor.g);
		vec2 texPos2;
		texPos2.x = (quad2.x * r_col) + 0.5/(col_s_pre_enlighten * dim_s_pre_enlighten ) + ((r_col - 1.0/(col_s_pre_enlighten * dim_s_pre_enlighten )) * textureColor.r);
		texPos2.y = (quad2.y * r_raw) + 0.5/(raw_s_pre_enlighten * dim_s_pre_enlighten ) + ((r_raw - 1.0/(raw_s_pre_enlighten * dim_s_pre_enlighten )) * textureColor.g);
		vec4 newColor1 = texture2D(tex_enlighten, texPos1);
		vec4 newColor2 = texture2D(tex_enlighten, texPos2);
		vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
		return newColor = vec4(mix(textureColor, vec4(newColor.rgb, textureColor.w), blend_factor).rgb,C.a);
}

void main(){
	vec4 origin_color = texture2D(tex_origin, st);
	vec4 mask_color = texture2D(tex_mask, st);
	vec4 ret_color = origin_color;
	if(mask_color.a > 0.001){
		//gl_FragColor = vec4(1.0,0.0,0.0,1.0);
		vec4 blur_color = texture2D(tex_pre_blur, st);
		blur_color.a = origin_color.a;
		float dis = distance(vec3(0.0,0.0,0.0),max(blur_color.rgb - origin_color.rgb,0.0)) * 30.0;
		float factor = 1.0;
		factor = step(0.5,dis);
		factor = (1.0 - smoothstep(5.0,6.0,dis)) * factor;
		if(dis > 0.5 && dis < 5.){
			ret_color = shader_main_s_pre_enlighten(origin_color,factor * blur_intensity * mask_color.a * 0.25);
		}
	}
	gl_FragColor = ret_color;
}

