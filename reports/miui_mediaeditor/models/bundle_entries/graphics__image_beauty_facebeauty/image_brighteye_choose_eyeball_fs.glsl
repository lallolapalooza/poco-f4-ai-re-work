varying vec2 texc;

void main()
{
    vec3 mask_color = texture2D(tex_eyemask,texc).rgb;
    vec3 eyeball_color = texture2D(tex_eyeball,texc).rgb;
    gl_FragColor = vec4(clamp(mask_color - eyeball_color,0.0,1.0),1.0);
}