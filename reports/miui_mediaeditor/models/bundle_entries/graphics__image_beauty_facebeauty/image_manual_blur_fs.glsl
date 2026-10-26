varying vec2 texc;
varying vec2 texf;

void main() {
    vec4 blur_color = texture2D(tex_blur,texc);
    float blur_alpha = blur_color.a;
    //if(blur_alpha <= 0.2)
    //    blur_alpha = 0.2;
    float mask_alpha = texture2D(tex_manual_mask,texf).r;
    mask_alpha = clamp(mask_alpha,0.0,1.0);
    float protect_mask_alpha = texture2D(tex_protect_manual_mask,texf).g;
    //vec3 blur_color = texture2D(tex_blur,texc).rgb;
    vec4 origin_color = texture2D(tex_origin,texc);
    vec3 result_color = mix(origin_color.rgb,blur_color.rgb,blur_alpha * mask_alpha /* * protect_mask_alpha */ * blur_rate_scale);
    gl_FragColor = vec4(result_color,origin_color.a);
}