varying vec2 texc;

void main()
{
    vec3 hbColor = texture2D(tex_heavyblur,texc).rgb;
    vec3 sbColor = texture2D(tex_slightblur,texc).rgb;
    float rate_temp = length(clamp(hbColor - sbColor,0.0,1.0));
    if(rate_temp > 0.0)
        gl_FragColor = vec4(1.0,1.0,1.0,1.0);
    else
        gl_FragColor = vec4(0.0,0.0,0.0,1.0);
}