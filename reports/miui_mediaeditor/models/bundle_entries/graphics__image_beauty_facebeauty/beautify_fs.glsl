vec4 shader_main(vec2 st, vec4 C){
    vec4 maskColor = texture2D(tex_mask,st);
    float pouch_mask = 0.0;
    float fold_mask = 0.0;

    if(maskColor.b > 0.0 && maskColor.g > 0.0 && maskColor.r < 0.001){
        pouch_mask = maskColor.b;
    }

    if(maskColor.r > 0.0 && maskColor.g <= 0.1){
        fold_mask = maskColor.r;
    }

    vec3 color = C.rgb;

#ifdef DEBUG_MASK
    color = mix(color,vec3(0.0,1.0,1.0), intensity_pouch*pouch_mask);
    color = mix(color,vec3(1.0,0.0,0.0), intensity_fold*fold_mask);
    return vec4(color,C.a);
#endif

    vec3 scaledColor;
    vec3 scaledBlurColor;
    if(intensity_fold > 0.0 || intensity_pouch > 0.0){
        scaledColor = texture2D(tex_inputScaled, st).rgb;
        scaledBlurColor = texture2D(tex_inputScaledBlur, st).rgb;
    }

    if(intensity_pouch > 0.0){//uniform branch
        vec3 imDiff = clamp((scaledBlurColor - scaledColor) * 1.4 + 0.05 * scaledBlurColor, 0.0, 0.15);
        imDiff = min(color + imDiff, 1.0);
        color = mix(color, imDiff, intensity_pouch * pouch_mask * 0.8);
    }

    if(intensity_fold > 0.0){//uniform branch
        vec3 imDiff = clamp((scaledBlurColor - scaledColor) * 1.4 + 0.05 * scaledBlurColor, 0.0, 0.2);
        imDiff = min(color + imDiff, 1.0);

        float temp0 = 0.0;
        float temp1 = 0.8;
        float b = color.b;
        float light_alpha = 1.0;
        if(b > temp0 && b <= temp1)
        {
            light_alpha *= 1.0 - (b - temp0) / (temp1 - temp0);
        }
        else if(b > temp1)
        {
            light_alpha *= 1.0;
        }
        if(light_alpha >= 0.35)
            light_alpha *= 1.5;
        if(light_alpha >= 1.0) light_alpha = 1.0;

        float temp = fold_mask + (fold_mask * fold_mask * fold_mask - fold_mask) * (1.0 - light_alpha);
        color = mix(color, imDiff, intensity_fold * temp * 0.8);
    }


    return vec4(color, C.a);
}
