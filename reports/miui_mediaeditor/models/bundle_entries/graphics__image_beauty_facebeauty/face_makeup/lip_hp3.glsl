void main()
{
    vec4 maskColor = texture2D(u_lipColortexture, st);

    vec4 hPassImage = texture2D(u_highpassTexture, st);

    float yawPowCoff = tan(radians(clamp(abs(u_yawAngle * 2.0), 45.0, u_clampPowMaxYaw)));
    float yawLinearCoff = smoothstep(0.0, 1.0, clamp(abs(u_yawAngle * 2.0), 45.0, u_clampLinearMaxYaw) / 90.0) * 2.0;
    float specPow = pow(max(v_weight, 0.0) , u_lipGlossSpecPowFactor / yawPowCoff) * u_lipGlossSpecFactor * yawLinearCoff;
   // float highlight = clamp(maskColor.g * (hPassImage.g) * specPow, 0.0, 1.0);
   //float highlight = clamp(maskColor.g *  0.5, 0.0, 1.0);
    float highlight = clamp((1.0 - maskColor.r) * (hPassImage.g) * specPow, 0.0, 1.0);
    //float highlight = clamp((1.0 - maskColor.r) * specPow, 0.0, 1.0);
    gl_FragColor = vec4(vec3(highlight) * maskColor.a,1.0);
}