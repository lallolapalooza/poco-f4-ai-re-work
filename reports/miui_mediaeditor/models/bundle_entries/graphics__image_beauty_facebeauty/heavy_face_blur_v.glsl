vec4 shader_main(vec2 st, vec4 C)
{
    float p = 1.0*4.0;
	float softenLevel = ratio;
	vec2 blurCoordinates[4];
	vec2 blurCoordinates2[8];
	vec2 stride = vec2(sizeInvW,sizeInvH)*image_ratio_to_std_size*blur_amount;
	vec4 pupu=vec4(sizeInvW,sizeInvH,sizeInvW,sizeInvH)*image_ratio_to_std_size*blur_amount;
	vec4 stst=vec4(st,st);
	vec4 bc0_bc1 = stst + vec4(vec2(0.0,-8.0) , vec2(0.0,-4.0)) * pupu ;
	vec4 bc2_bc3 = stst + vec4(vec2(0.0,4.0) , vec2(0.0,8.0)) * pupu ;
   

	vec4 resultColor = texture2D(tex_beautified, st );
    resultColor += texture2D(tex_beautified, bc0_bc1.xy);
    resultColor += texture2D(tex_beautified, bc0_bc1.zw);
    resultColor += texture2D(tex_beautified, bc2_bc3.xy);
    resultColor += texture2D(tex_beautified, bc2_bc3.zw);
    resultColor /= 5.0;
	
	bc0_bc1 = stst + vec4(vec2(-8.0,0.0) , vec2(8.0,0.0)) * pupu;
	bc2_bc3 = stst + vec4(vec2(0.0,8.0) , vec2(0.0,-8.0)) * pupu;
	//vec4 bc4_bc5 = stst + vec4(vec2(8.0,0.0) , vec2(8.0,-8.0)) * pupu;
	//vec4 bc6_bc7 = stst + vec4(vec2(0.0,-8.0) , vec2(-8.0,-8.0)) * pupu;
	//有点类似双边滤波的思想，模糊后的点跟周围点的差距越大，权重越小。所以会具有一定的保边能力
	vec4 color = C;
    float var = (color.g - resultColor.g)*(color.g - resultColor.g);
	vec4 group1 = vec4(texture2D(tex, bc0_bc1.xy).g , texture2D(tex, bc0_bc1.zw).g,texture2D(tex, bc2_bc3.xy).g , texture2D(tex, bc2_bc3.zw).g) - resultColor.g;
	//vec4 group2 = vec4(texture2D(tex, bc4_bc5.xy).g , texture2D(tex, bc4_bc5.zw).g,texture2D(tex, bc6_bc7.xy).g , texture2D(tex, bc6_bc7.zw).g) - resultColor.g;
	var += dot(group1,group1); 
	//var += dot(group2,group2); 
    var /= 5.0; float a = max(0.0, min(1.0,var / (var + 0.0036 * softenLevel)));
    if (skin_detect > 0.0)
    {
        float pr = color.r;
        float pg = color.g;
        float pb = color.b;
        if (pr > 0.371 && pg > 0.156 && pb > 0.078 && pr > pb && pr > pg && abs(pr-pg)>0.059 && max(max(pr,pg),pb)-min(min(pr,pg),pb) > 0.059) {
            ;
        }
        else
        {
            a = 0.5 * (1.0 + a);
        }
    }
    vec3 src = color.rgb;
    vec3 msk = resultColor.rgb;
	if(is_rgba < 0.5)
		msk = msk.bgr;
	vec4 result = vec4(src*a+msk*(1.0-a),1.0);

	return result;
}