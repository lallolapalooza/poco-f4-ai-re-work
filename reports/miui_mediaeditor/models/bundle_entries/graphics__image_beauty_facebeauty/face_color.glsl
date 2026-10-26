
vec4 shader_main_s_color_shader(vec2 st_src,vec4 C){
	if(color_level_s_color_shader < 0.001)
	{
		return C;
	}
	else
	{
	vec4 C_origin = C;
	C *= color_scale_s_color_shader;
#ifdef OPENG_ES
	highp vec2 st=C.xy*(15.0/16.0)+(0.5/16.0);//clamp(C.xy,0.5/16.0,15.5/16.0);
#else
	vec2 st=C.xy*(15.0/16.0)+(0.5/16.0);//clamp(C.xy,0.5/16.0,15.5/16.0);
#endif
	st.y=stripHeight_s_color_shader*(filterIdx_s_color_shader+st.y);

	float b16=C.z*15.0 + 0.1;//C.z*(255.0/16.0);	
	float slice0=min(floor(b16),15.0);
	float slice1=min(slice0+1.0,15.0);
	float slice_w=b16-slice0;
	vec3 color0=texture2D(tex_filter_s_color_shader,vec2((slice0+st.x)*(1.0/16.0),st.y)).xyz;
	vec3 color1=texture2D(tex_filter_s_color_shader,vec2((slice1+st.x)*(1.0/16.0),st.y)).xyz;
	C=vec4(color0+slice_w*(color1-color0), C.w);
	C = C_origin + (C - C_origin) * color_level_s_color_shader;
	
	return vec4(C.xyz,C_origin.a);	
	}
}

