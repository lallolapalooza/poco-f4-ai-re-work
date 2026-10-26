varying vec2 texc;

void main()
{
    vec3 oriblur_color = texture2D(tex_blur,texc).rgb;   
    vec3 ori_color = texture2D(tex_origin,texc).rgb;
    vec3 sharpen_color = ori_color + (ori_color - oriblur_color) * 0.3;
    gl_FragColor = vec4(sharpen_color,1.0);
}