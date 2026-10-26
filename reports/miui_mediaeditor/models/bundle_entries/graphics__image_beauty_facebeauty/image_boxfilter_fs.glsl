varying vec2 texc;

varying vec4 sampleuv[6];

vec4 BoxFliterBlurCumulate(vec4 sum, vec4 suv) {
    float pi = 3.1415916;
    vec3 color = texture2D(tex_origin, suv.xy).rgb;
    float weight = 1.0;
    sum.xyz += color * weight;
    sum.w += weight;
    color = texture2D(tex_origin, suv.zw).rgb;
    weight = 1.0;
    sum.xyz += color * weight;
    sum.w += weight;
    return sum;
}

void main() {
    float pi = 3.1415916;
    float r = blur_num;
    vec4 color0 = texture2D(tex_origin, texc);
    vec4 sum = vec4(0.0);
    for (int i = 0; i < 6; i++) {
        vec4 temp_sampleuv = sampleuv[i] * r + vec4(texc,texc); 
        sum = BoxFliterBlurCumulate(sum, temp_sampleuv);
    }
    float weight = 1.0;
    sum.xyz += color0.xyz * weight;
    sum.w += weight;
    if(sum.w != 0.0){
		sum.xyz /= sum.w;
        gl_FragColor = vec4(sum.xyz,1.0);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
	}	
}