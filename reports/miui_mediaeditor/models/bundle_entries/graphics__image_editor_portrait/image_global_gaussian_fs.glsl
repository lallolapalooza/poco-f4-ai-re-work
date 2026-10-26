varying vec2 texc;
varying vec4 sampleuv[5];

float weight_sum = 0.0;
vec4 GaussianBlurCumulate(vec4 sum, vec4 suv,float weight) {
    float pi = 3.1415916;
    vec4 color = texture2D(tex_origin, suv.xy);
    sum += color * weight;
    weight_sum += weight;
    color = texture2D(tex_origin, suv.zw);
    sum += color * weight;
    weight_sum += weight;
    return sum;
}

void main() {
    vec4 color0 = texture2D(tex_origin, texc);
    vec4 sum = vec4(0.0);
    for (int i = 0; i < 5; i++) {
        sum = GaussianBlurCumulate(sum, sampleuv[i],float(arrfloat_weight[i]));
    }
    float weight = float(arrfloat_weight[0]);
    sum += color0 * weight;
    weight_sum += weight;
    sum /= weight_sum;
    gl_FragColor = vec4(sum.rgb,1.0);
}