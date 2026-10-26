varying vec2 texc;
varying vec2 texf;

varying vec4 sampleuv[5];


vec4 GaussianBlurCumulate(vec4 sum, vec4 suv,float weight) {
    float pi = 3.1415916;
    vec3 color = texture2D(tex_origin, suv.xy).rgb;
    sum.xyz += color * weight;
    sum.w += weight;
    color = texture2D(tex_origin, suv.zw).rgb;
    sum.xyz += color * weight;
    sum.w += weight;
    return sum;
}

void main() {
    float pi = 3.1415916;
    int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float r = arrfloat_r[face_idx - 1] / 3.0;
    vec4 color0 = texture2D(tex_origin, texc);
    vec4 sum = vec4(0.0);
    for (int i = 0; i < 5; i++) {
        vec4 temp_sampleuv = sampleuv[i] * r + vec4(texc,texc); 
        sum = GaussianBlurCumulate(sum, temp_sampleuv,float(arrfloat_weight[i]));
    }
    float weight = float(arrfloat_weight[0]);
    sum.xyz += color0.xyz * weight;
    sum.w += weight;
    sum.xyz /= sum.w;

    gl_FragColor = vec4(sum.xyz, 1.0); 
}
