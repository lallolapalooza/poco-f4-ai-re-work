varying vec2 stf_block;
const vec3 LUMINANCE_COEFF = vec3(0.299, 0.587, 0.114);
const float OFFSET_SCALE = 1.35;
const float OFFSET_SCALE_FOR_NONE_BLENDING = 2.15;

const float LOW_LUM_THRESHOLD = 0.355;

const float HALF_WEIGHT = 0.5;
const float QUARTER_WEIGHT = 0.25;

float getColorOffset(vec4 fgColor, bool noneBlendng)
{
    if (u_meanLuminance > u_theshold) {
        return 0.0;
    }

    float colorOffset = 0.0;
    float fgLuminance = dot(fgColor.rgb / fgColor.a, LUMINANCE_COEFF);
     colorOffset = (-0.40862638 * fgLuminance) + (0.81462644 * pow(u_meanLuminance, 2.0)) + 0.03340745; 

    if (u_meanLuminance < LOW_LUM_THRESHOLD && noneBlendng) { //none blending
        colorOffset -= ((LOW_LUM_THRESHOLD - u_meanLuminance) * 1.85);
    }
    
    return colorOffset * (noneBlendng ? OFFSET_SCALE_FOR_NONE_BLENDING : OFFSET_SCALE);
}


float getColorOffsetLens(vec4 fgColor)
{
    if (u_meanLuminance > u_theshold) {
        return 0.0;
    }

    float colorOffset = 0.0;
    float fgLuminance = dot(fgColor.rgb / fgColor.a, LUMINANCE_COEFF);
    colorOffset = (-0.17929308 * fgLuminance) + (1.8824009 * pow(u_meanLuminance, 2.0)) - 0.47202639;

    return colorOffset;
}

vec3 adaptiveMakeup(vec4 fgColor, float colorOffsetScale)
{
    float colorOffset = getColorOffset(fgColor, false) * colorOffsetScale;
    vec3 originColor = clamp(fgColor.rgb * (1.0 / fgColor.a), 0.0, 1.0);
    return clamp(originColor + vec3(colorOffset), 0.0, 1.0);
}

float adaptiveMakeupForAlpha(vec4 fgColor, float baseAlpha, bool noneBlending)
{
    return clamp(baseAlpha - abs(getColorOffset(fgColor, noneBlending)), 0.0, 1.0);
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


vec4 lut16(in vec4 origin, float intensity, sampler2D lut, in float strength)
{
    intensity = clamp(intensity, 0.0, 1.0);
    
    float blueColor = origin.b * 15.0;
    vec2 quad1 = vec2(0.0,0.0);
    quad1.y = floor(floor(blueColor) / 4.0);
    quad1.x = floor(blueColor) - (quad1.y * 4.0);
    
    vec2 quad2;
    quad2.y = floor(ceil(blueColor) / 4.0);
    quad2.x = ceil(blueColor) - (quad2.y * 4.0);
    
    vec2 texPos1;
    
    texPos1.x = (quad1.x * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * origin.r);
    texPos1.y = (quad1.y * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * origin.g);
    vec2 texPos2;
    
    texPos2.x = (quad2.x * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * origin.r);
    texPos2.y = (quad2.y * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * origin.g);
    texPos1.y = 1.0 - texPos1.y;
    texPos2.y = 1.0 - texPos2.y;
    
    vec4 newColor1 = texture2D(lut, texPos1);
    vec4 newColor2 = texture2D(lut, texPos2);
    
    vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
    
    newColor = lipcolor;
    float weight = intensity * strength * sign(length(newColor));
    return mix(origin, newColor, weight);
}
// void main()
// {
//     const float DELTA = 0.005;
// 	float v_weight = 1.0;
//     float lipOpacity = texture2D(tex_lipMask, uv).a * v_weight;
    
//     vec4 origin = texture2D(tex, stf);
//     //vec4 blurImage;
//     vec4 resultColor = origin;

//     // if (u_enableBlur) {
//     //     blurImage = texture2D(u_blurImage, v_vertCoord);
//     //     resultColor = mix(origin, blurImage, texture2D(u_blurMask, v_texCoord).r * u_mixOriginAndBlurRatio);
//     // }
        
//     if (lipOpacity > DELTA) { //lip
//         float extraAlpha = 1.0;
//         extraAlpha = clamp(1.0 - abs(getColorOffset(resultColor, true)), 0.0, 1.0); // BLEND 
//         extraAlpha = 1.0;
//         resultColor.rgb = lut16(resultColor, lipOpacity, tex_lipLut, lipLut * extraAlpha).rgb;
//     }

// if (lipOpacity > DELTA) {
//     if (u_enableGlossDelta > 0.5) {
//         vec3 delta = texture2D(tex_glossDelta, uv).rgb;
//         //delta = clamp((delta - 0.5) * 2.0 , -1.0, 1.0);
//         resultColor.rgb = clamp(resultColor.rgb + delta * v_weight , 0.0, 1.0);
//     }
// }
//     gl_FragColor = resultColor;
// }

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
	vec4 alpha_combine =texture2D(tex_lipMask, uv );
	vec4 alpha0 = alpha_combine.rgba;
	vec4 alpha1 = texture2D(tex_lipMask, uv + stride_w).rgba;
	vec4 alpha2 = texture2D(tex_lipMask, uv - stride_w).rgba;
	vec4 alpha3 = texture2D(tex_lipMask, uv + stride_h).rgba;
	vec4 alpha4 = texture2D(tex_lipMask, uv - stride_h).rgba;
	vec4 alpha5 = texture2D(tex_lipMask, uv + stride_w+stride_h).rgba;
	vec4 alpha6 = texture2D(tex_lipMask, uv - stride_w+stride_h).rgba;
	vec4 alpha7 = texture2D(tex_lipMask, uv + stride_w-stride_h).rgba;
	vec4 alpha8 = texture2D(tex_lipMask, uv - stride_w-stride_h).rgba;
	
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
	
	float gray = 0.299 * source.r + 0.587 * source.g + 0.114 * source.b;
	float intensity = clamp(gg - gray,0.0,1.0) * 10.0;
	float contrast = max(min(0.5 * contrast_mask + 0.5 * (1.0 - contrast_mask), 1.0), 0.0); 
	if (contrast < 0.5) contrast = 0.5 - (0.5 - contrast);

	 {
		float blend_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).r;
		float level_weight = texture2D(tex_blend_weight_and_level_map, vec2(gray, 0.0)).g * alpha;
        if(lip_blend_weight_type > 0.5){
			blend_weight = 1.0;
			level_weight = alpha;
		}
		//vec3 t = lipcolor.rgb * source;
		//vec3 color = mix(t, lipcolor.rgb, blend_weight);
        vec3 color = blend_all(source,lipcolor.rgb,blend_weight,blend_type_tex_lip);
		dst_color = mix(source, color, level_weight);
	} 
	float diff = max(gray - 1.0, 0.0) * alpha;
	dst_color = vec3(1.0) - (vec3(1.0) - dst_color) * vec3(1.0 - diff); 
	dst_color.r = HardLight(dst_color.r, contrast);
	dst_color.g = HardLight(dst_color.g, contrast);
	dst_color.b = HardLight(dst_color.b, contrast);  
	dst_color = vec3(1.0) - (vec3(1.0) - dst_color) * vec3(1.0);
    if (alpha > 0.005) {
	if (u_enableGlossDelta > 0.5) {
        vec3 delta = texture2D(tex_glossDelta, uv).rgb;
        //delta = clamp((delta - 0.5) * 2.0 , -1.0, 1.0);
        dst_color.rgb = clamp(dst_color.rgb + delta, 0.0, 1.0);
    }
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