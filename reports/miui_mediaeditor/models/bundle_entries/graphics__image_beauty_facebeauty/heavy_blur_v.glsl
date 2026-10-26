
vec4 shader_main(sampler2D tex,vec2 st)
{
	vec2 stride=sizeInv*image_ratio_to_std_size;
    vec4 pupu=vec4(sizeInv,sizeInv)*image_ratio_to_std_size;
	vec4 stst=vec4(st,st);
	vec4 bc0_bc1 = (stst + vec4(vec2(0.0,-8.0), vec2(0.0,-4.0)) * pupu) ;
	vec4 bc2_bc3 = (stst + vec4(vec2(0.0,4.0), vec2(0.0,8.0)) * pupu);
	vec4 resultColor = texture2D(tex, st);
    resultColor += texture2D(tex, bc0_bc1.xy);
    resultColor += texture2D(tex, bc0_bc1.zw);
    resultColor += texture2D(tex, bc2_bc3.xy);
    resultColor += texture2D(tex, bc2_bc3.zw);
    resultColor *= 0.2;
	return resultColor;
}