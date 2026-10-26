varying vec2 texc;

void main()
{
    vec3 I_color = texture2D(tex_I,texc).rgb;
    vec3 I2_color = texture2D(tex_I2,texc).rgb;
    vec3 I2out_color = texture2D(tex_I2out,texc).rgb;
    float a = texture2D(tex_mask,texc).r;
    vec3 result_color = I_color - I2_color + I2out_color;
    gl_FragColor = vec4(result_color,a);
}