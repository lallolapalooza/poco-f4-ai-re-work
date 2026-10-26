#ifdef OPENG_ES
precision highp float;
#endif
varying vec2 stf_block;
const float	flt_epsilon	= 0.001;
const mat3		RGBToYCbCr	= mat3(
	0.299, -0.169, 0.5,
	0.587, -0.331, -0.419,
	0.114, 0.5, -0.081
	);
const vec3 RGBToY = vec3( 0.299, 0.587, 0.114 );

vec3 RGBtoHCV( vec3 rgb )
{
	vec4	p	= (rgb.g < rgb.b) ? vec4( rgb.bg, -1.0, 0.66666667 ) : vec4( rgb.gb, 0.0, -0.33333333 );
	vec4	q	= (rgb.r < p.x) ? vec4( p.xyw, rgb.r ) : vec4( rgb.r, p.yzx );
	float	c	= q.x - min( q.w, q.y );
	float	h	= abs( (q.w - q.y) / (6.0 * c + flt_epsilon) + q.z );
	return(vec3( h, c, q.x ) );
}


vec3 RGBtoHSL( vec3 rgb )
{
	vec3	hcv	= RGBtoHCV( rgb );
	float	l	= hcv.z - hcv.y * 0.5;
	float	s	= hcv.y / (1.0 - abs( l * 2.0 - 1.0 ) + flt_epsilon);
	return(vec3( hcv.x, s, l ) );
}


vec3 HSLtoRGB( vec3 hsl )
{
	vec3	rgb;
	float	x = hsl.x * 6.0;
	rgb.r	= abs( x - 3.0 ) - 1.0;
	rgb.g	= 2.0 - abs( x - 2.0 );
	rgb.b	= 2.0 - abs( x - 4.0 );
	rgb	= clamp( rgb, 0.0, 1.0 );
	float c = (1.0 - abs( 2.0 * hsl.z - 1.0 ) ) * hsl.y;
	rgb = clamp( (rgb - vec3( 0.5 ) ) * vec3( c ) + vec3( hsl.z ), 0.0, 1.0 );
	return(rgb);
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
void main()
{
		vec2 st = stf;
		vec4 C = texture2D(tex,st);
		vec4 color1 = vec4(0.0);
		vec4 color12 = vec4(0.0);
		vec4 color2 = vec4(0.0);
		vec4 color3 = vec4(0.0);
		vec4 color4 = vec4(0.0);
		if( makeup_intensity1 > 0.001)
		{
			if(makeup_saihong1 > 0.5)
				color1 = texture2D(tex_makeup1,uv);
			if(makeup_saihong2 > 0.5)
				color12 = texture2D(tex_makeup12,uv);
		}
		if( makeup_intensity2 > 0.001)
		{
			color2 = texture2D(tex_makeup2,uv);
		}
		if( makeup_intensity3 > 0.001)
		{
			color3 = texture2D(tex_makeup3,uv);
		}
		if( makeup_intensity4 > 0.001)
		{
			color4 = texture2D(tex_makeup4,uv);
		}
		if(reverse_alpha > 0.5)
		{
			if(update1 > 0.5 && makeup_intensity1 > 0.001)
			{
				color1.rgb = color1.rgb / color1.a;
				color1.rgb = clamp(color1.rgb,0.0,1.0);
			}
			if(update12 > 0.5 && makeup_intensity1 > 0.001)
			{
				color12.rgb = color12.rgb / color12.a;
				color12.rgb = clamp(color12.rgb,0.0,1.0);
			}
			if(update2 > 0.5&& makeup_intensity2 > 0.001)
			{
				color2.rgb = color2.rgb / color2.a;
				color2.rgb = clamp(color2.rgb,0.0,1.0);
			}
			if(update3 > 0.5&& makeup_intensity3 > 0.001)
			{
				color3.rgb = color3.rgb / color3.a;
				color3.rgb = clamp(color3.rgb,0.0,1.0);
			}
			if(update4 > 0.5&& makeup_intensity4 > 0.001)
			{
				color4.rgb = color4.rgb / color4.a;
				color4.rgb = clamp(color4.rgb,0.0,1.0);
			}
		}
		float factor1 = color1.a * makeup_intensity1 ;
		float factor12 = color12.a * makeup_intensity1 ;
		float factor2 = color2.a * makeup_intensity2;
		float factor3 = color3.a * makeup_intensity3 ; 
		float factor4 = color4.a * makeup_intensity4 ;
		vec3 output1 = C.rgb;
		if(makeup_color1.a > 0.001)
		{
			factor1 *=  makeup_color1.a;
			color1.rgb  = makeup_color1.rgb;
		}
		if(makeup_color12.a > 0.001)
		{
			factor12 *=  makeup_color12.a;
			color12.rgb  = makeup_color12.rgb;
		}
		if(makeup_color2.a > 0.001)
		{
			vec3	source		= C.rgb;
			vec3	source_yuv	= RGBToYCbCr * source + vec3( 0.0, 0.5, 0.5 );
			float	mask_value = 0.0;
			if(color2.a > 0.1)
			   mask_value	= 0.5 ;
			 else 
				mask_value	= color2.a *5.0; 
			float	skin_prob;
			if ( mask_value < 0.002 || mask_value > 0.998 )
			{
				skin_prob = mask_value;
			}else  {
				vec3	y_cb_cr_diff		= abs( source_yuv - skin_y_cb_cr_average );
				vec3	y_cb_cr_diff_remap	= (y_cb_cr_diff - vec3( 0.3, 0.03, 0.02 ) ) / vec3( 0.05, 0.03, 0.02 );
				vec2	y_uv_prob		= vec2( 1.0 ) - clamp( vec2( y_cb_cr_diff_remap.x, max( y_cb_cr_diff_remap.y, y_cb_cr_diff_remap.z ) ), 0.0, 1.0 );
				skin_prob = y_uv_prob.r * y_uv_prob.g;
				const float breaking_offset_from_128 = 0.25;
				if ( mask_value < 0.5 )
				{
					mask_value	= max( 0.0, mask_value * 2.0 - 0.5 + breaking_offset_from_128 );
					skin_prob	= mix( skin_prob * mask_value, mask_value + mask_value * skin_prob * (1.0 - mask_value), mask_value );
				}else  {
					mask_value	= mask_value + (breaking_offset_from_128 * (1.0 - mask_value) / 0.5);
					skin_prob	= mask_value + mask_value * skin_prob * (1.0 - mask_value) * 0.5;
				}
			}
			if ( skin_prob < 0.00002 )
			{
				output1 = source;
			}else  {
				float mouth_anti_alpha = 1.0;
				skin_prob *= mouth_anti_alpha;
				float	left_eye_anti_alpha	= 1.0 ;
				float	right_eye_anti_alpha	= 1.0 ;
				skin_prob *= (left_eye_anti_alpha * right_eye_anti_alpha);
				float foundation_scale = makeup_intensity2 * 0.15;
				float	dst_weight	= foundation_scale * skin_prob;
				float	src_weight	= 1.0 - dst_weight;
				if ( source_yuv.x > highlight_th )
				{
					const float	EPSILON = 0.001;
					float		slope	= (highlight - highlight_th) / max( 1.0 - highlight_th, EPSILON );
					float		new_y	= mix( highlight_th, source_yuv.x, slope );
					new_y	= mix( source_yuv.x, new_y, skin_prob * foundation_scale );
					source	+= vec3( new_y - source_yuv.x );
				}
				float	src_luma			= (source.r + source.g + source.b) / 3.0;
				float	dst_reduction_shift		= 0.2;
				float	dst_reduction_shift_ratio	= 1.0 - max( min( (src_luma - 0.0392) / 0.1569, 1.0 ), 0.0 );
				float	dst_weight_reduction_shift	= dst_reduction_shift * dst_reduction_shift_ratio;
				dst_weight = max( dst_weight - dst_weight_reduction_shift, 0.0 );
				float	contrast_factor		= foundation_scale * 0.7;
				float	source_y		= dot( RGBToY, source );
				float	constrasted_source_y	= mix( source_y, (source_y - skin_y_cb_cr_average.r) * (1.0 + contrast_factor) + skin_y_cb_cr_average.r, skin_prob );
				vec3	constrasted_source	= source + vec3( constrasted_source_y - source_y );
				vec3	foundation_output	= mix( constrasted_source, makeup_color2.rgb, dst_weight );
				foundation_output = max( min( foundation_output, 1.0 ), 0.0 );
				output1 = foundation_output;
			}
			//output1 = vec3(skin_prob);
			//factor2 *=  makeup_color2.a;
			//color2.rgb  = makeup_color2.rgb;
		}
		if(makeup_color3.a > 0.001)
		{
			factor3 *=  makeup_color3.a;
			color3.rgb  = makeup_color3.rgb;
		}
		if(makeup_color4.a > 0.001)
		{
			factor4 *=  makeup_color4.a;
			color4.rgb  = makeup_color4.rgb;
		}
		vec3 out_color = output1;

		vec3 grat_temp = vec3(0.3,0.59,0.11);
		float gray = dot(out_color,grat_temp);
		float gray_rate = 0.0;
		gray_rate = texture2D(tex_curve_mask,vec2(gray,0.0)).r;
		gray_rate = clamp(gray_rate,0.0,1.0);
		gray_rate = 1.0;

		out_color = blend_all(out_color,color1.rgb,factor1,blend_type_tex_blusher);
		out_color = blend_all(out_color,color12.rgb,factor12,blend_type_tex_blusher2);

	
			
		//out_color = mix(out_color.rgb, color1.rgb, factor1);
		//gaoguang add
		out_color = blend_all(out_color,color3.rgb,factor3 * gray_rate,blend_type_tex_highlight);
		//out_color = add(out_color,color3.rgb,factor3);
		//out_color = mix(out_color.rgb, color3.rgb, factor3);
		//yinying multiply
		out_color = blend_all(out_color,color4.rgb,factor4 * gray_rate,blend_type_tex_shadow);
		//out_color = multiple(out_color,color4.rgb,factor4);
		//out_color = mix(out_color.rgb, color4.rgb, factor4);

		//out_color = mix(out_color, C.rgb, 1.0 - occu_value);	
		
		//gl_FragColor = vec4(out_color.rgb, 1.0); 
		//gl_FragColor=C * (1.0 - C1.a) + C1 * C1.a;
		//gl_FragColor=vec4(1.0,0.0,0.0,1.0);
		float mask_color = 0.0;
		if (stf_block.x >= offset.x && stf_block.x <= offset.x + wh.x && stf_block.y >= offset.y &&
			stf_block.y <= offset.y + wh.y) {
			vec2 makeup_texv = stf_block - offset;
			makeup_texv.x /= wh.x;
			makeup_texv.y /= wh.y;
			mask_color = texture2D(tex_makeup_mask,makeup_texv).r;
		}
		gl_FragColor = vec4(mix(C.rgb,out_color,mask_color),1.0);

}