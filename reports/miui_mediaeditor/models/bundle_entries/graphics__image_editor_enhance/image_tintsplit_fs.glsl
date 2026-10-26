varying vec2 texc;
varying vec2 texf;
precision highp float;
vec4 lut_color (vec4 C,sampler2D tex_lut){
	float color_scale_s_color_custom_shader_whitten = 1.0;
	float raw_s_color_custom_shader_whitten = 8.0;
	float col_s_color_custom_shader_whitten = 8.0;
	float dim_s_color_custom_shader_whitten = 64.0;
	vec4 textureColor = C;
	textureColor *= color_scale_s_color_custom_shader_whitten  ;
	float blueColor = textureColor.b * (dim_s_color_custom_shader_whitten   - 1.0);
	float r_raw = 1.0 / raw_s_color_custom_shader_whitten  ;
	float r_col = 1.0 / col_s_color_custom_shader_whitten  ;
	vec2 quad1;
	quad1.y = floor(floor(blueColor) / raw_s_color_custom_shader_whitten  );
	quad1.x = floor(blueColor) - (quad1.y * col_s_color_custom_shader_whitten  );
	vec2 quad2; 
	quad2.y = floor(ceil(blueColor) / raw_s_color_custom_shader_whitten  );
	quad2.x = ceil(blueColor) - (quad2.y * col_s_color_custom_shader_whitten  );
	vec2 texPos1;      
	texPos1.x = (quad1.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos1.y = (quad1.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec2 texPos2;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	texPos2.x = (quad2.x * r_col) + 0.5/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )  + ((r_col - 1.0/(col_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.r);
	texPos2.y = (quad2.y * r_raw) + 0.5/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  ) + ((r_raw - 1.0/(raw_s_color_custom_shader_whitten   * dim_s_color_custom_shader_whitten  )) * textureColor.g);
	vec4 newColor1 = texture2D(tex_lut  , texPos1);
	vec4 newColor2 = texture2D(tex_lut  , texPos2);
	vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
	return vec4(newColor1.rgb,C.a);
}

vec3 multiple(vec3 origin,vec3 target,float alpha)
{
	alpha *= 0.7;
	target /= 255.0;
	vec3 C_res=origin*(1.0-alpha)+origin*target*alpha;
	return C_res;
}

vec4 do_tintsplit(vec4 in_color) {
	vec3 color = in_color.rgb;
	color = multiple(color, color_blue_highlight, blue_highlight);
	color = multiple(color, color_blue_shadow, blue_shadow);
	color = multiple(color, color_green_highlight, green_highlight);
	color = multiple(color, color_green_shadow, green_shadow);
	color = multiple(color, color_orange_highlight, orange_highlight);
	color = multiple(color, color_orange_shadow, orange_shadow);
	color = multiple(color, color_purple_highlight, purple_highlight);
	color = multiple(color, color_purple_shadow, purple_shadow);
	color = multiple(color, color_red_highlight, red_highlight);
	color = multiple(color, color_red_shadow, red_shadow);
	color = multiple(color, color_yellow_highlight, yellow_highlight);
	color = multiple(color, color_yellow_shadow, yellow_shadow);
	return vec4(color,in_color.a);

	//vec4 color = in_color;
	// vec4 highligt_color = lut_color(color, tex_lut_h1);
	// vec4 shadow_color = lut_color(color, tex_lut_l1);
	// color = mix(mix(color, highligt_color, blue_highlight), shadow_color, blue_shadow);

	// highligt_color = lut_color(color, tex_lut_h2);
	// shadow_color = lut_color(color, tex_lut_l2);
	// color = mix(mix(color, highligt_color, red_highlight), shadow_color, red_shadow);

	// highligt_color = lut_color(color, tex_lut_h3);
	// shadow_color = lut_color(color, tex_lut_l3);
	// color = mix(mix(color, highligt_color, yellow_highlight), shadow_color, yellow_shadow);

	// highligt_color = lut_color(color, tex_lut_h4);
	// shadow_color = lut_color(color, tex_lut_l4);
	// color = mix(mix(color, highligt_color, purple_highlight), shadow_color, purple_shadow);

	// highligt_color = lut_color(color, tex_lut_h5);
	// shadow_color = lut_color(color, tex_lut_l5);
	// color = mix(mix(color, highligt_color, orange_highlight), shadow_color, orange_shadow);

	// highligt_color = lut_color(color, tex_lut_h6);
	// shadow_color = lut_color(color, tex_lut_l6);
	// color = mix(mix(color, highligt_color, green_highlight), shadow_color, green_shadow);
	//return color;
}

void main(){
	vec4 incolor = texture2D(tex_input, texc);
    vec4 o_color = incolor;
	incolor = do_tintsplit(incolor);
	vec4 mask_color = texture2D(tex_mask,texf);
	gl_FragColor = vec4(mix(o_color.rgb,incolor.rgb,clamp(mask_color.a,0.0,1.0)), incolor.a);
}
