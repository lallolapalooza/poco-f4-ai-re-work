
void main() 
{
	gl_Position=vec4(P_quad.xy*2.0-1.0,0.0,1.0);
	vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);
	st = P_quad.xy;
	textureShift_1 = vec4(P_quad.xy - singleStepOffset, P_quad.xy + singleStepOffset);
	textureShift_2 = vec4(P_quad.xy - 2.0 * singleStepOffset, P_quad.xy + 2.0 * singleStepOffset);
	textureShift_3 = vec4(P_quad.xy - 3.0 * singleStepOffset, P_quad.xy + 3.0 * singleStepOffset);
	textureShift_4 = vec4(P_quad.xy - 4.0 * singleStepOffset, P_quad.xy + 4.0 * singleStepOffset);
}