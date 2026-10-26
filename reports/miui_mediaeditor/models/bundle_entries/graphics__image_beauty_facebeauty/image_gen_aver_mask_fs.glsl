varying vec2 texc;

void main()
{
    vec3 I2_color = texture2D(tex_I2,texc).rgb;
    vec3 B_color = texture2D(tex_B,texc).rgb;
    vec3 C_color = texture2D(tex_C,texc).rgb;
    vec3 factor = vec3(0.299, 0.587, 0.114);
    float cgray = dot(C_color, factor);
    float i2gray = dot(I2_color, factor);
    float alpha = texture2D(tex_mask,texc).r;
    if (cgray > i2gray) {
        I2_color += (C_color - I2_color)* (0.7 * alpha);
    }
    else{
        if(type < 0.5){
            float value = texture2D(tex_highlight_mask,texc).r;
            if (value > 0.0) {
                float r = smoothstep(0.55, 0.8, i2gray);
                I2_color += (C_color - I2_color)* (alpha * (1.0 - r) * 0.3);
            } else {
                I2_color += (C_color - I2_color) * 0.3;
            }
        }
        else{
            I2_color += (C_color - I2_color) * 0.3;
        }
    }
    gl_FragColor = vec4(I2_color,1.0);
}