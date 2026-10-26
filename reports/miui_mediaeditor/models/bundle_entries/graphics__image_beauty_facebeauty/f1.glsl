void main() 
{
	vec3 sum = texture2D(tex_src, st).rgb;
	sum += texture2D(tex_src, textureShift_1.xy).rgb;
	sum += texture2D(tex_src, textureShift_1.zw).rgb;
	sum += texture2D(tex_src, textureShift_2.xy).rgb;
	sum += texture2D(tex_src, textureShift_2.zw).rgb;
	sum += texture2D(tex_src, textureShift_3.xy).rgb;
	sum += texture2D(tex_src, textureShift_3.zw).rgb;
	sum += texture2D(tex_src, textureShift_4.xy).rgb;
	sum += texture2D(tex_src, textureShift_4.zw).rgb;
	gl_FragColor = vec4(sum * 0.11111, 1.0);
}