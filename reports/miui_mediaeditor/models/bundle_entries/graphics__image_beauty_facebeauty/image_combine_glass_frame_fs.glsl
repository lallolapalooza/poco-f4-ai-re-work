varying vec2 texc;

void main()
{
    vec3 skin_color = texture2D(tex_skin,texc).rgb;
    vec3 glass_color = texture2D(tex_glass,texc).rgb;
    vec3 outcolor = clamp(skin_color - glass_color,0.0,1.0);
    gl_FragColor = vec4(outcolor, 1.0);
}