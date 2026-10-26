varying vec2 texc;

void main()
{
    float alpha = texture2D(tex_mask,texc).r;
    vec3 aver_color = texture2D(tex_aver,texc).rgb;
    vec3 noacen_color = texture2D(tex_noacen,texc).rgb;
    vec3 outcolor = clamp(noacen_color + aver_color - 0.5,0.0,1.0);
    gl_FragColor = vec4(outcolor,alpha);
}