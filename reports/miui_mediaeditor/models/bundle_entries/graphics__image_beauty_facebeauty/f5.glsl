void main() 
{
	vec4 iColor = texture2D(tex_src, st);
	vec4 meanColor = texture2D(tex_mean, st);
	vec4 varColor = texture2D(tex_diff, st);
	float faceSkinMask = 0.0;
	float p = max(faceSkinMask,clamp((min(iColor.r, meanColor.r - 0.1) - 0.2) * 4.0, 0.0, 1.0));
	float nevusMaskColor = 1.0;
	float skinMask = 1.0;
	float theta = 0.1;
	float meanVar = (varColor.r + varColor.g + varColor.b) / 3.0;
	float kMin;
	vec3 resultColor;
	kMin = (1.0 - meanVar / (meanVar + theta)) * p * blurAlpha;
	resultColor = mix(iColor.rgb, meanColor.rgb, kMin * nevusMaskColor * skinMask);
	gl_FragColor = vec4(resultColor.rgb, 1.0);
}