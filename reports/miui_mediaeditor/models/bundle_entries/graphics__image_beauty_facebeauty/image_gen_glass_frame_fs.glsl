varying vec2 texc;

void main()
{
    vec3 glass_erode = texture2D(tex_erode,texc).rgb;
    vec3 glass_dilate = texture2D(tex_dilate,texc).rgb;
    vec3 result = clamp(glass_dilate - glass_erode,0.0,1.0);
    gl_FragColor = vec4(result,1.0);
}