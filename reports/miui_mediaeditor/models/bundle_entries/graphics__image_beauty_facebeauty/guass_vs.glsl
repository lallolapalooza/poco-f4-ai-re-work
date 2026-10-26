void main()
{
    vUV = P_quad.xy;
    vec2 singleStepOffset = vec2(widthOffset, heightOffset);
    textureShift_0 = vec4(vUV - 0.5 * singleStepOffset, vUV + 0.5 * singleStepOffset);
    textureShift_1 = vec4(vUV - singleStepOffset, vUV + singleStepOffset);
    textureShift_2 = vec4(vUV - 1.5 * singleStepOffset, vUV + 1.5 * singleStepOffset);
    textureShift_3 = vec4(vUV - 2.0 * singleStepOffset, vUV + 2.0 * singleStepOffset);
    textureShift_4 = vec4(vUV - 2.3 * singleStepOffset, vUV + 2.3 * singleStepOffset);
    gl_Position=vec4(P_quad.xy*2.0-1.0,0.0,1.0);
}