varying vec2 texc;

void main()
{
    vec3 origin_color = texture2D(tex_origin,texc).rgb;
    vec3 blur_color = texture2D(tex_blur,texc).rgb;
    vec3 outcolor = clamp(origin_color - blur_color + 0.5,0.0,1.0);
    gl_FragColor = vec4(outcolor,1.0);
}