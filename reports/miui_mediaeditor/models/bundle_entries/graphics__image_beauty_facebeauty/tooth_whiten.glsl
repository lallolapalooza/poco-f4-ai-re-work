float cross_calc(vec2 a,vec2 b)
{
	return a.x*b.y - b.x*a.y;
}
vec4 lut(sampler2D lutTexture, vec3 textureColor) 
{ 
     float blueColor = textureColor.b * 15.0; 
     vec2 quad1; 
     quad1.y = max(min(4.0,floor(floor(blueColor) * 0.25)),0.0); 
     quad1.x = max(min(4.0,floor(blueColor) - (quad1.y * 4.0)),0.0); 
 
     vec2 quad2; 
     quad2.y = max(min(floor(ceil(blueColor) * 0.25),4.0),0.0); 
     quad2.x = max(min(ceil(blueColor) - (quad2.y * 4.0),4.0),0.0); 
 
     vec2 texPos1; 
     texPos1.x = (quad1.x * 0.25) + 0.0078125 + ((0.234375) * textureColor.r); 
     texPos1.y = (quad1.y * 0.25) + 0.0078125 + ((0.234375) * textureColor.g); 
     texPos1.y =  texPos1.y;
 
     vec2 texPos2; 
     texPos2.x = (quad2.x * 0.25) + 0.0078125 + ((0.234375) * textureColor.r); 
     texPos2.y = (quad2.y * 0.25) + 0.0078125 + ((0.234375) * textureColor.g);
     texPos2.y =  texPos2.y;
 
     vec4 newColor1 = texture2D(lutTexture, texPos1); 
     vec4 newColor2 = texture2D(lutTexture, texPos2); 
 
     vec4 newColor = mix(newColor1, newColor2, fract(blueColor)); 
     return newColor; 
}
vec4 lut2(vec4 C){
	vec4 C_origin = C;
	//C *= color_scale;
#ifdef OPENG_ES
	highp vec2 st=C.xy*(15.0/16.0)+(0.5/16.0);//clamp(C.xy,0.5/16.0,15.5/16.0);
#else
	vec2 st=C.xy*(15.0/16.0)+(0.5/16.0);//clamp(C.xy,0.5/16.0,15.5/16.0);
#endif
	//st.y=stripHeight*(filterIdx+st.y);

	float b16=C.z*15.0 + 0.1;//C.z*(255.0/16.0);	
	float slice0=min(floor(b16),15.0);
	float slice1=min(slice0+1.0,15.0);
	float slice_w=b16-slice0;
	vec3 color0=texture2D(tex_lut2_s_tooth_whiten_shader ,vec2((slice0+st.x)*(1.0/16.0),st.y)).xyz;
	vec3 color1=texture2D(tex_lut2_s_tooth_whiten_shader ,vec2((slice1+st.x)*(1.0/16.0),st.y)).xyz;
	C=vec4(color0+slice_w*(color1-color0), C.w);
	C = C_origin + (C - C_origin) * 1.0;
	
	return vec4(C.xyz,1.0);	
} 
vec4 shader_main_s_tooth_whiten_shader (vec2 st, vec4 C)
{
	vec2 curcood = st * dims_s_tooth_whiten_shader ;

	if(sharpness_s_tooth_whiten_shader  > 0.001)
	{
		vec2 realMouthCenter = (p62_s_tooth_whiten_shader+p59_s_tooth_whiten_shader)*0.5;
		float p = 1.0*4.0;
		vec2 stride = vec2(sizeInvW_s_tooth_whiten_shader ,sizeInvH_s_tooth_whiten_shader )*image_ratio_to_std_size_s_tooth_whiten_shader ;
		vec4 pupu=vec4(sizeInvW_s_tooth_whiten_shader ,sizeInvH_s_tooth_whiten_shader ,sizeInvW_s_tooth_whiten_shader ,sizeInvH_s_tooth_whiten_shader )*image_ratio_to_std_size_s_tooth_whiten_shader ;
		vec4 stst=vec4(st,st);
		vec4 bc0_bc1 = stst + vec4(vec2(0.0,-8.0) , vec2(-8.0,0.0)) * pupu ;
		vec4 bc2_bc3 = stst + vec4(vec2(8.0,0.0) , vec2(0.0,8.0)) * pupu ;
		float radius =standardLength_s_tooth_whiten_shader *0.55;;//0.35
		float intensity = 0.0;

		vec2 dst = curcood - realMouthCenter;
		intensity = sharpness_s_tooth_whiten_shader  * mouth_open_intensity_s_tooth_whiten_shader ;
		float pr = C.r;
		float pg = C.g;
		float pb = C.b;

		vec4 white = vec4(tooth_color_s_tooth_whiten_shader ,1.0);
		//vec4 brightenColor = lut(tex_lut, C.rgb );
		vec4 brightenColor = lut2(C);

		vec3 mask =texture2D(tex_mask,st).rgb;
		float inner_mouth_mask = 0.0;
		if(mask.r > 0.0 && mask.g > 0.001){
			inner_mouth_mask = mask.g;
		}
#ifdef DEBUG_MASK
		return vec4(mix(C.rgb,vec3(1.0,1.0,0.0),intensity*inner_mouth_mask),C.a);
#endif
		float finalIntensity = intensity * inner_mouth_mask;
		vec4 res = mix(C,brightenColor,finalIntensity);
		res.a = C.a;
		return res;
	}
	else 
	{
		return C;
	}
}