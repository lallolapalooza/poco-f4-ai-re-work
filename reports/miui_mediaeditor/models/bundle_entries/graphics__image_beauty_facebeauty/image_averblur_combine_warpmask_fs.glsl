varying vec2 texc;

void main()
{
    vec3 cal_mask = texture2D(tex_cal,texc).rgb;
    vec3 warp_mask = texture2D(tex_warp,texc).aaa;
    gl_FragColor = vec4(cal_mask * warp_mask,1.0);
}