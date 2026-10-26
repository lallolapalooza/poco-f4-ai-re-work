//meiyan3
float pow03(float x)
{
	//[0.2388 3.5048 -11.4242 21.4566 -19.507 6.7431]
	return 0.2388+x*(3.5048+x*(-11.4242+x*(21.4566+x*(-19.507+x*6.7431))));
}
float pow033(float x)
{
	//[0.2032 3.5250 -11.3382 21.2904 -19.3648 6.6954]
	return 0.2032+x*(3.5250+x*(-11.3382+x*(21.2904+x*(-19.3648+x*6.6954))));
}
float pow039(float x)
{
	//[0.1518 3.3386 -10.0630 18.5685 -16.7500 5.7637]
	return 0.1518+x*(3.3386+x*(-10.0630+x*(18.5685+x*(-16.7500+x*5.7637))));
}
vec3 pow096(vec3 x)
{
	//[0.0055 1.0426 -0.0499]
	return 0.0055+x*(1.0426+x*-0.0499);
}
vec4 Coeffi4(vec4 s, float central, float coeffi)
{
	return (-3.6*coeffi) * min(abs(vec4(central) - s), vec4(1.0/3.6)) + vec4(coeffi);
}
vec4 shader_main(sampler2D tex,vec2 st){
	vec4 pupu=vec4(sizeInv.x,sizeInv.y,sizeInv.x,sizeInv.y)*image_ratio_to_std_size;
	vec4 stst=vec4(st,st);
	
	vec3 centralColor = texture2D(tex, st).rgb;
	float central = centralColor.g;
	vec4 gwTotal4=vec4(0.2,0.0,0.0,0.0);
	vec4 sum4=central*gwTotal4;
	vec4 s4;
	vec4 gw4;
	/////////
	vec4 bc0_bc1 = stst + 2.0*vec4(0.0,-10.0,5.0,-8.0)*pupu;
	vec4 bc2_bc3 = stst + 2.0*vec4(8.0,-5.0,10.0,0.0)*pupu;
	s4=vec4(
		texture2D(tex, bc0_bc1.xy).g,
		texture2D(tex, bc0_bc1.zw).g,
		texture2D(tex, bc2_bc3.xy).g,
		texture2D(tex, bc2_bc3.zw).g);
	gw4 = Coeffi4(s4, central, 0.08); gwTotal4 += gw4; sum4 += s4 * gw4;
	
	vec4 bc4_bc5 = stst + 2.0*vec4(8.0,5.0,5.0,8.0)*pupu;
	vec4 bc6_bc7 = stst + 2.0*vec4(0.0,10.0,-5.0,8.0)*pupu;
	s4=vec4(
		texture2D(tex, bc4_bc5.xy).g,
		texture2D(tex, bc4_bc5.zw).g,
		texture2D(tex, bc6_bc7.xy).g,
		texture2D(tex, bc6_bc7.zw).g);
	gw4 = Coeffi4(s4, central, 0.08); gwTotal4 += gw4; sum4 += s4 * gw4;
	
	vec4 bc8_bc9 = stst + 2.0*vec4(-8.0,5.0,-10.0,0.0)*pupu;
	vec4 bc10_bc11 = stst + 2.0*vec4(-8.0,-5.0,-5.0,-8.0)*pupu;
	s4=vec4(
		texture2D(tex, bc8_bc9.xy).g,
		texture2D(tex, bc8_bc9.zw).g,
		texture2D(tex, bc10_bc11.xy).g,
		texture2D(tex, bc10_bc11.zw).g);
	gw4 = Coeffi4(s4, central, 0.08); gwTotal4 += gw4; sum4 += s4 * gw4;
	
	vec4 bc12_bc13 = stst + 1.6*vec4(0.0,-6.0,-4.0,-4.0)*pupu;
	vec4 bc14_bc15 = stst + 1.6*vec4(-6.0,0.0,-4.0,4.0)*pupu;
	s4=vec4(
		texture2D(tex, bc12_bc13.xy).g,
		texture2D(tex, bc12_bc13.zw).g,
		texture2D(tex, bc14_bc15.xy).g,
		texture2D(tex, bc14_bc15.zw).g);
	gw4 = Coeffi4(s4, central, 0.1); gwTotal4 += gw4; sum4 += s4 * gw4;
	
	vec4 bc16_bc17 = stst + 1.6*vec4(0.0,6.0,4.0,4.0)*pupu;
	vec4 bc18_bc19 = stst + 1.6*vec4(6.0,0.0,4.0,-4.0)*pupu;
	s4=vec4(
		texture2D(tex, bc16_bc17.xy).g,
		texture2D(tex, bc16_bc17.zw).g,
		texture2D(tex, bc18_bc19.xy).g,
		texture2D(tex, bc18_bc19.zw).g);
	gw4 = Coeffi4(s4, central, 0.1); gwTotal4 += gw4; sum4 += s4 * gw4;
	
	float sum = dot(sum4,vec4(1.0))/dot(gwTotal4,vec4(1.0));
	
	float s = centralColor.g - sum + 0.5;
	float s0=s;
	if(s0>0.5){s=1.0-s;}
	s*=2.0;
	s*=s;s*=s;s*=s;s*=s;s*=s;
	s*=0.5;
	if(s0>0.5){s=1.0-s;}
	
	float diff = abs(central-sum);
	//float aa = 1.0 + pow03(sum)*0.09;
	//float aa = 1.0 + pow03(sum)*0.09*(localC0*100.0*diff*diff+1.0);
	float aa = 1.0 + pow03(sum)*(9.0*diff*diff+0.09);
	
	vec3 smoothColor = centralColor*aa - vec3(s)*(aa-1.0);// get smooth color
	smoothColor = clamp(smoothColor,vec3(0.0),vec3(1.0));//make smooth color right
	vec3 smoothColor0 = smoothColor;
	
	smoothColor = mix(centralColor, smoothColor, pow033(centralColor.g));
	smoothColor = mix(centralColor, smoothColor, pow039(centralColor.g));
	smoothColor = mix(centralColor, smoothColor, 0.79);
	
	float scale = diff*20.0;
	scale = clamp(scale, 0.0, 1.0);
	smoothColor = mix(smoothColor, smoothColor0, scale);
	
	return vec4(pow096(smoothColor),1.0);
}