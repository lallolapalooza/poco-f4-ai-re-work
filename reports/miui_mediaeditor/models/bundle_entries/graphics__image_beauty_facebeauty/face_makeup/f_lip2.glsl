#ifdef OPENG_ES
precision highp float;
#endif
varying vec2 stf_block;
float flt_epsilon = 0.001;
vec3 RGBtoHCV(vec3 rgb) {
    vec4 p = (rgb.g < rgb.b) ? vec4(rgb.bg, -1.0, 0.66666667) : vec4(rgb.gb, 0.0, -0.33333333);
    vec4 q = (rgb.r < p.x) ? vec4(p.xyw, rgb.r) : vec4(rgb.r, p.yzx);
    float c = q.x - min(q.w, q.y);
    float h = abs((q.w - q.y) / (6.0 * c + flt_epsilon) + q.z);
    return vec3(h, c, q.x);
}
vec3 RGBtoHSL(vec3 rgb) {
    vec3 hcv = RGBtoHCV(rgb);
    float l = hcv.z - hcv.y * 0.5;
    float s = hcv.y / (1.0 - abs(l * 2.0 - 1.0) + flt_epsilon);
    return vec3(hcv.x, s, l);
}
vec3 HSLtoRGB(vec3 hsl) {
    vec3 rgb;
    float x = hsl.x * 6.0;
    rgb.r = abs(x - 3.0) - 1.0;
    rgb.g = 2.0 - abs(x - 2.0);
    rgb.b = 2.0 - abs(x - 4.0);
    rgb = clamp(rgb, 0.0, 1.0);
    float c = (1.0 - abs(2.0 * hsl.z - 1.0)) * hsl.y;
    rgb = clamp((rgb - vec3(0.5)) * vec3(c) + vec3(hsl.z), 0.0, 1.0);
    return rgb;
}
float HardLight(float color, float layer) {
    if (color < 0.5) color = 2.0 * layer * color;
    else color = 1.0 - 2.0 * (1.0 - layer) * (1.0 - color);
    return color;
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
#define mtrxSize 3

float neighborhoods[mtrxSize*mtrxSize];
float sortNeighborhoods()
{
	float max0 = -1.0;
	float max1 = -1.0;
	float max2 = -1.0;
	float max3 = -1.0;
	float max4 = -1.0;
	for (int j=0; j < (mtrxSize*mtrxSize -1); j++)
	{
		if (max0 < neighborhoods[j])
		{ 
			max0 = neighborhoods[j];
		}
	}

	for (int j=0; j < (mtrxSize*mtrxSize -1); j++)
	{
		if (max1 < neighborhoods[j] && neighborhoods[j] < max0)
		{ 
			max1 = neighborhoods[j];
		}
	}

	for (int j=0; j < (mtrxSize*mtrxSize -1); j++)
	{
		if (max2 < neighborhoods[j] && neighborhoods[j] < max1)
		{ 
			max2 = neighborhoods[j];
		}
	}

	for (int j=0; j < (mtrxSize*mtrxSize -1); j++)
	{
		if (max3 < neighborhoods[j] && neighborhoods[j] < max2)
		{ 
			max3 = neighborhoods[j];
		}
	}

	for (int j=0; j < (mtrxSize*mtrxSize -1); j++)
	{
		if (max4 < neighborhoods[j] && neighborhoods[j] < max3)
		{ 
			max4 = neighborhoods[j];
		}
	}
	return max4;
}
vec3 sobelFilter(vec2 st,sampler2D inputTex,vec2 stride)
{
	vec2 uv = st;
    vec3 col;
    
    /*** Sobel kernels ***/
    // Note: GLSL's mat3 is COLUMN-major ->  mat3[col][row]
    mat3 sobelX = mat3(-1.0, -2.0, -1.0,
                       0.0,  0.0, 0.0,
                       1.0,  2.0,  1.0);
    mat3 sobelY = mat3(-1.0,  0.0,  1.0,
                       -2.0,  0.0, 2.0,
                       -1.0,  0.0,  1.0);  
    
    float sumX = 0.0;	// x-axis change
    float sumY = 0.0;	// y-axis change
    
    for(int i = -1; i <= 1; i++)
    {
        for(int j = -1; j <= 1; j++)
        {
            // texture coordinates should be between 0.0 and 1.0
            float x = uv.x + (float(i)/0.5)*stride.x ;	
    		float y =  uv.y + (float(j)/0.5)*stride.y;
            
            // Convolve kernels with image
            sumX += length(texture2D( inputTex, vec2(x, y) ).xyz) * float(sobelX[1+i][1+j]);
            sumY += length(texture2D( inputTex, vec2(x, y) ).xyz) * float(sobelY[1+i][1+j]);
        }
    }
    
    float g = clamp(abs(sumX) * 3.0 ,0.0,1.0);
    //g = sqrt((sumX*sumX) + (sumY*sumY));
    
    
    
	return vec3(g);
}


void main() {
    vec3 source = texture2D(tex, stf).rgb;
	vec3 dst_color = source;
	vec4 alpha_combine =texture2D(tex_makeup, uv );
	vec4 alpha0 = alpha_combine.rgba;
	vec4 alpha1 = texture2D(tex_makeup, uv + stride_w).rgba;
	vec4 alpha2 = texture2D(tex_makeup, uv - stride_w).rgba;
	vec4 alpha3 = texture2D(tex_makeup, uv + stride_h).rgba;
	vec4 alpha4 = texture2D(tex_makeup, uv - stride_h).rgba;
	vec4 alpha5 = texture2D(tex_makeup, uv + stride_w+stride_h).rgba;
	vec4 alpha6 = texture2D(tex_makeup, uv - stride_w+stride_h).rgba;
	vec4 alpha7 = texture2D(tex_makeup, uv + stride_w-stride_h).rgba;
	vec4 alpha8 = texture2D(tex_makeup, uv - stride_w-stride_h).rgba;
	
	vec4 alpha_ave = (alpha0+alpha1+alpha2+alpha3+alpha4+alpha5+alpha6+alpha7+alpha8)*0.1111111  ;
	
	vec4 alpha_gloss = alpha0*0.2 + alpha1*0.2+alpha2*0.2+alpha3*0.2+alpha4*0.2;
	float alpha = alpha_ave.a * makeup_intensity;
	
	vec3 sum_source = source;
	sum_source += texture2D(tex, stf + stride_w2).rgb ;
	sum_source += texture2D(tex, stf - stride_w2).rgb;
	sum_source += texture2D(tex, stf + stride_h2).rgb;
	sum_source += texture2D(tex, stf - stride_h2).rgb;
	sum_source += texture2D(tex, stf + stride_w2+stride_h2).rgb;
	sum_source += texture2D(tex, stf - stride_w2+stride_h2).rgb;
	sum_source += texture2D(tex, stf + stride_w2-stride_h2).rgb;
	sum_source += texture2D(tex, stf - stride_w2-stride_h2).rgb;
	sum_source *= 0.11111111;
	
	float sobel_result =clamp(alpha_combine.b*3.0  ,0.0,1.0);
	
	float gg = 0.299 * sum_source.r + 0.587 * sum_source.g + 0.114 * sum_source.b;
	
	
	float contrast_mask =  alpha_combine.a;
	//float gloss = texture2D(tex_makeup, uv).b;
	//float contrast_mask = 1.0;
	float noise = texture2D(tex_noise, uv*0.2).r *10.0;
	float gloss = ( alpha_ave.g*0.5 + alpha_combine.g*0.6 ) * gloss_intensity * 0.7;
	//gloss = gloss ;
	//gloss = gloss*(1.0-sobel_result);
	gloss = clamp(gloss,0.0,1.0); 
	
	
	float gray = 0.299 * source.r + 0.587 * source.g + 0.114 * source.b;
	float intensity = clamp(gg - gray,0.0,1.0) * 10.0;
	float contrast = max(min((gg * gloss_contrast_scale + gloss_contrast_shift) * contrast_mask + 0.5 * (1.0 - contrast_mask), 1.0), 0.0); 
	if (contrast < 0.5) contrast = 0.5 - (0.5 - contrast) * gloss_contrast_shrink;
	if (use_median > 0.5) {
	
		vec3 source1 = texture2D(tex, stf + stride_w2).rgb;
		vec3 source2 = texture2D(tex, stf - stride_w2).rgb;
		vec3 source3 = texture2D(tex, stf + stride_h2).rgb;
		vec3 source4 = texture2D(tex, stf - stride_h2).rgb;
		vec3 source5 = texture2D(tex, uv + stride_w2+stride_h2).rgb;
		vec3 source6 = texture2D(tex, uv - stride_w2+stride_h2).rgb;
		vec3 source7 = texture2D(tex, uv + stride_w2-stride_h2).rgb;
		vec3 source8 = texture2D(tex, uv - stride_w2-stride_h2).rgb;
		neighborhoods[0] = 0.299 * source.r + 0.587 * source.g + 0.114 * source.b;
		neighborhoods[1] = 0.299 * source1.r + 0.587 * source1.g + 0.114 * source1.b;
		neighborhoods[2] = 0.299 * source2.r + 0.587 * source2.g + 0.114 * source2.b;
		neighborhoods[3] = 0.299 * source3.r + 0.587 * source3.g + 0.114 * source3.b;
		neighborhoods[4] = 0.299 * source4.r + 0.587 * source4.g + 0.114 * source4.b;
		neighborhoods[5] = 0.299 * source5.r + 0.587 * source5.g + 0.114 * source5.b;
		neighborhoods[6] = 0.299 * source6.r + 0.587 * source6.g + 0.114 * source6.b;
		neighborhoods[7] = 0.299 * source7.r + 0.587 * source7.g + 0.114 * source7.b;
		neighborhoods[8] = 0.299 * source8.r + 0.587 * source8.g + 0.114 * source8.b;
		//sortNeighborhoods();
		gloss = alpha_ave.b * gloss_intensity;
		
		gray = sortNeighborhoods();
	}
	if (lipstick_layer_count < 1.5) {
		float blend_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).r;
		float level_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).g * alpha;
		if(lip_blend_weight_type > 0.5){
			blend_weight = 1.0;
			level_weight = alpha;
		}
		//vec3 color = mix(lipstick_color_0 * source, lipstick_color_0, blend_weight);
		vec3 color = blend_all(source,lipstick_color_0,blend_weight,blend_type_tex_lip);
		dst_color = mix(source, color, level_weight);
	} else if (lipstick_layer_count < 2.5) {
		if (lipstick_is_upper_lower_omber < 0.5) {
			float alpha_0 = alpha_ave.r * makeup_intensity;
			float blend_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).r;
			float level_weight_0 = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).g;
			float level_weight_1 = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).b;
			//float level_weight =  alpha_0 * alpha;
			if(lipstick_color_1.r <0.001 &&lipstick_color_1.g<0.001&&lipstick_color_1.b<0.001)
			{
				float level_weight = alpha_0;
				vec3 color = lipstick_color_0;
				//color = mix(color * source, color, blend_weight);
				color = blend_all(source,color,blend_weight,blend_type_tex_lip);
				dst_color = mix(source, color, level_weight);
			}
			else
			{
				float level_weight = mix(0.8, 0.8, alpha_0) * alpha;
				vec3 color = mix(lipstick_color_1, lipstick_color_0, alpha_0);
				//color = mix(color * source, color, blend_weight);
				color = blend_all(source,color,blend_weight,blend_type_tex_lip);
				dst_color = mix(source, color, level_weight);
			}
			
			
		} else if (lipstick_is_upper_lower_omber < 1.5) {
			float alpha_0 = texture2D(tex_lipstick_layer, uv).r;
			float alpha_1 = texture2D(tex_lipstick_layer, uv).g;
			float tmp = alpha_0;
			alpha_0 = alpha_0 * (1.0 - alpha_1);
			alpha_1 = alpha_1 * (1.0 - tmp);
			float alpha_sum = alpha_0 + alpha_1;
			if (alpha_sum > 0.0) {
				alpha_0 = alpha_0 / alpha_sum;
				alpha_1 = alpha_1 / alpha_sum;
			} else {
				alpha_0 = alpha_1 = 0.0;
			}
			float blend_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).r;
			float level_weight_0 = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).g;
			float level_weight_1 = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).b;
			float level_weight = (alpha_0 * level_weight_0 + alpha_1 * level_weight_1) * alpha;
			vec3 color = lipstick_color_0 * alpha_0 + lipstick_color_1 * alpha_1;
			//color = mix(color * source, color, blend_weight);
			color = blend_all(source,color,blend_weight,blend_type_tex_lip);
			dst_color = mix(source, color, level_weight);
		}
	}
	float diff = max(gray - force_bright_threshold, 0.0) * alpha;
	/* dst_color = vec3(1.0) - (vec3(1.0) - dst_color) * vec3(1.0 - diff); 
	dst_color.r = HardLight(dst_color.r, contrast);
	dst_color.g = HardLight(dst_color.g, contrast);
	dst_color.b = HardLight(dst_color.b, contrast);  
	dst_color = vec3(1.0) - (vec3(1.0) - dst_color) * vec3(1.0 - gloss); */
	vec3 gloss_color = dst_color;
	gloss_color = vec3(1.0) - (vec3(1.0) - gloss_color) * vec3(1.0 - diff); 
	gloss_color.r = HardLight(gloss_color.r, contrast);
	gloss_color.g = HardLight(gloss_color.g, contrast);
	gloss_color.b = HardLight(gloss_color.b, contrast);  
	gloss_color = vec3(1.0) - (vec3(1.0) - gloss_color) * vec3(1.0 - gloss);
	dst_color = mix(dst_color,gloss_color,makeup_intensity);
	vec2 uv2 = uv ;
	float transition_ratio = (min(max(abs(uv2.x - 0.5), 0.083), 0.5) - 0.083) / 0.417;
	float luma_weight = gray * 3.0;
	float shimmer_weight = 1.0 - 0.3 * transition_ratio;
	float shimmer = texture2D(tex_lipstick_shimmer, uv2*0.75).r;
	//vec3 hsl = RGBtoHSL(dst_color);
	//hsl.z = min(hsl.z + shimmer * shimmer_weight * luma_weight * contrast_mask, 1.0);
	//dst_color = HSLtoRGB(hsl);	
	float contrastshimmer = contrast*1.2;
	if(shimmer>0.01)
	{
		shimmer=shimmer*shimmer_weight*luma_weight;
		if(alpha_combine.b < 0.5) 
		{
			contrastshimmer=contrastshimmer*0.6;
			//dst_color = vec3(1.0,0.0,0.0);
		}
		else
		{
			contrastshimmer=min(1.0,contrastshimmer*1.2);
			//dst_color = vec3(0.0,1.0,0.0);
		}
		float alpha0 =0.0;
		if(alpha > 0.8) alpha0 = alpha;
		else if(alpha > 0.6) alpha0 = alpha * 0.5;
		contrastshimmer=contrastshimmer*alpha0;
		dst_color=mix(dst_color,vec3(1.0,0.94,1.0),contrastshimmer*shimmer);
	} 
	
	//gl_FragColor = vec4(dst_color, 1.0);
	float mask_color = 0.0;
	if (stf_block.x >= offset.x && stf_block.x <= offset.x + wh.x && stf_block.y >= offset.y &&
      	stf_block.y <= offset.y + wh.y) {
		vec2 makeup_texv = stf_block - offset;
		makeup_texv.x /= wh.x;
		makeup_texv.y /= wh.y;
		mask_color = texture2D(tex_makeup_mask,makeup_texv).r;
	}
	gl_FragColor = vec4(mix(source,dst_color,mask_color),1.0);
}