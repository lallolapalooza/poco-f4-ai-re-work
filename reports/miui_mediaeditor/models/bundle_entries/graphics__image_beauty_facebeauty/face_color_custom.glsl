
vec4 shader_main_s_color_custom_shader (vec2 st_src,vec4 C){
	if(color_level_s_color_custom_shader  < 0.001)
	{
		return C;
	}
	else
	{
	 vec4 textureColor = C;
	 textureColor *= color_scale_s_color_custom_shader ;
     float blueColor = textureColor.b * (dim_s_color_custom_shader  - 1.0);
     float r_raw = 1.0 / raw_s_color_custom_shader ;
	 float r_col = 1.0 / col_s_color_custom_shader ;
#ifdef OPENG_ES
	 highp  vec2 quad1;
#else
	 vec2 quad1;
#endif
     quad1.y = floor(floor(blueColor) / raw_s_color_custom_shader );
     quad1.x = floor(blueColor) - (quad1.y * col_s_color_custom_shader );
#ifdef OPENG_ES
	 highp  vec2 quad2;
#else
	 vec2 quad2;
#endif    
     quad2.y = floor(ceil(blueColor) / raw_s_color_custom_shader );
     quad2.x = ceil(blueColor) - (quad2.y * col_s_color_custom_shader );
#ifdef OPENG_ES
	 highp  vec2 texPos1;
#else
	 vec2 texPos1;
#endif      
     texPos1.x = (quad1.x * r_col) + 0.5/(col_s_color_custom_shader  * dim_s_color_custom_shader )  + ((r_col - 1.0/(col_s_color_custom_shader  * dim_s_color_custom_shader )) * textureColor.r);
     texPos1.y = (quad1.y * r_raw) + 0.5/(raw_s_color_custom_shader  * dim_s_color_custom_shader ) + ((r_raw - 1.0/(raw_s_color_custom_shader  * dim_s_color_custom_shader )) * textureColor.g);
#ifdef OPENG_ES
	 highp  vec2 texPos2;
#else
	 vec2 texPos2;
#endif                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
     texPos2.x = (quad2.x * r_col) + 0.5/(col_s_color_custom_shader  * dim_s_color_custom_shader )  + ((r_col - 1.0/(col_s_color_custom_shader  * dim_s_color_custom_shader )) * textureColor.r);
     texPos2.y = (quad2.y * r_raw) + 0.5/(raw_s_color_custom_shader  * dim_s_color_custom_shader ) + ((r_raw - 1.0/(raw_s_color_custom_shader  * dim_s_color_custom_shader )) * textureColor.g);
     vec4 newColor1 = texture2D(tex_filter_s_color_custom_shader , texPos1);
     vec4 newColor2 = texture2D(tex_filter_s_color_custom_shader , texPos2);
     
     vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
     return vec4(mix(textureColor, vec4(newColor.rgb, textureColor.w), color_level_s_color_custom_shader ).rgb,C.a);
	 }
}
