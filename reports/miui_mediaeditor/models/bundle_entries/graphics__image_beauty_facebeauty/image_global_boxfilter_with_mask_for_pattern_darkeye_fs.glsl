varying vec2 texc;

void main()
{
    vec3 color_sum = vec3(0.0);
    float weight_sum = 0.0;
    if(type == 0.0) {
        for (float i = -1.0 * blur_num; i < blur_num; i += 1.0)
        {
            vec2 pos = texc + vec2(i,0)*offset;
            float w = texture2D(tex_mask,pos).r;
            color_sum += texture2D(tex_origin,pos).rgb * w;
            weight_sum += w;
        }
    }
    else {
        for (float i = -1.0 * blur_num; i < blur_num; i += 1.0)
        {
            vec2 pos = texc + vec2(0,i)*offset;
            float w = texture2D(tex_mask,pos).r;
            color_sum += texture2D(tex_origin,pos).rgb * w;
            weight_sum += w;
        }
    }
    if(weight_sum != 0.0){
        color_sum /= weight_sum;
        gl_FragColor = vec4(color_sum,1.0);
    }
    else{
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb,1.0); 
    }
}