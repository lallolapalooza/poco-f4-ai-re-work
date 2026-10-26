varying vec2 texc;

void main()
{
    vec3 lip_mouth_color = texture2D(tex_lip_mouth,texc).rgb;
    vec3 lip_mouth_erode_color = texture2D(tex_lip_mouth_erode,texc).rgb;
    float lip = lip_mouth_erode_color.r - lip_mouth_color.g;
    gl_FragColor = vec4(vec3(lip),1.0);
}