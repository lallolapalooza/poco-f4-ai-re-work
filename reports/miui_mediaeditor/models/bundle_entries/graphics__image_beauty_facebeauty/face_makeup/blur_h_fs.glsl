
vec4 shader_main() {
    vec2 uv = st;
    float twoSigma2 = 2.0 * sigma * sigma;
    vec3 sum = texture2D(tex_color, uv).rgb;
    float norm = 1.0;
    int sampler_n = int(N);
    for(int i = 1; i <= sampler_n / 2; ++i){
        float kernel = exp(- float(i * i) * step * step / twoSigma2);
        norm += kernel * 2.0;
        sum += texture2D(tex_color, uv + vec2(1.0, 0.0) * float(i) * step / LIP_MASK_SIZE).rgb * kernel;
        sum += texture2D(tex_color, uv - vec2(1.0, 0.0) * float(i) * step / LIP_MASK_SIZE).rgb * kernel;
    }
    sum /= norm;
    //sum = texture2D(tex_color, uv).rgb;
    return vec4(sum, 1.0);
}