varying vec2 texc;

void main()
{
    vec3 oriblur_color = texture2D(tex_oriblur,texc).rgb;   
    vec3 ori_color = texture2D(tex_origin,texc).rgb;
    // vec3 sharpen_color = ori_color + (ori_color - oriblur_color) * 1.0;
    float weight = 0.6;
	  vec3 sharpen_color = (ori_color - weight * oriblur_color) / (1.0 - weight);
    sharpen_color = clamp(sharpen_color,0.0,1.0);
    sharpen_color = mix(ori_color,sharpen_color,1.0);
    gl_FragColor = vec4(sharpen_color,1.0);
}