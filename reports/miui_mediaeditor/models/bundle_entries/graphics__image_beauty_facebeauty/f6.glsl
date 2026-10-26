void main() 
{
	vec3 iColor = texture2D(tex_src, st).rgb;
	vec3 meanColor = texture2D(tex_mean, st).rgb;
	vec3 diffColor = iColor - meanColor;
	diffColor = min(diffColor, 0.0);
	iColor += (diffColor + 0.015) * clarityAlpha;
	iColor = max(iColor, 0.0);
	gl_FragColor = vec4(iColor, 1.0);
}