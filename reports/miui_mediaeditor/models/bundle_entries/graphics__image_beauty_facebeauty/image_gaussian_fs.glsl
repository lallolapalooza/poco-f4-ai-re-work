varying vec2 texc;

/* void main()
{
    float pi = 3.1415916;
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float sigma = arrfloat_sigma[face_idx - 1];
    float r = arrfloat_r[face_idx - 1];
    if(type == 0.0)
    {
        vec4 outcolor = vec4(0.0);
        float w_sum = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            float w = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
            outcolor += texture2D(tex_origin,vec2(texc.x+i*offset.x,texc.y)) * w;
            w_sum += w;
        }
        if(w_sum <= 0.001){
            gl_FragColor = texture2D(tex_origin,texc);
        }
        else{
            outcolor /= w_sum;
            gl_FragColor = outcolor;
        }
    }
    else
    {
        vec4 outcolor = vec4(0.0);
        float w_sum = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            float w = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
            outcolor += texture2D(tex_origin,vec2(texc.x,texc.y+i*offset.y)) * w;
            w_sum += w;
        }
        if(w_sum <= 0.001){
            gl_FragColor = texture2D(tex_origin,texc);
        }
        else{
            outcolor /= w_sum;
            gl_FragColor = outcolor;
        }
    }
} */

varying vec4 sampleuv[5];
//varying float samplei[6];
float weight_sum = 0.0;

vec4 GaussianBlurCumulate(vec4 sum, vec4 suv,float weight) {
    float pi = 3.1415916;
    vec4 color = texture2D(tex_origin, suv.xy);
    //float weight = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
    sum += color * weight;
	weight_sum += weight;
    color = texture2D(tex_origin, suv.zw);
    sum += color * weight;
    weight_sum += weight;
    return sum;
}

void main() {
    float pi = 3.1415916;
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    //float sigma = arrfloat_sigma[face_idx - 1];
    float r = arrfloat_r[face_idx - 1] / 3.0;
    vec4 color0 = texture2D(tex_origin, texc);
    vec4 sum = vec4(0.0);
    for (int i = 0; i < 5; i++) {
        //float radiusi = samplei[i] * r;
        vec4 temp_sampleuv = sampleuv[i] * r + vec4(texc,texc); 
        sum = GaussianBlurCumulate(sum, temp_sampleuv,float(arrfloat_weight[i]));
    }
    //float weight = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * 0.0 * 0.0/(2.0*sigma*sigma));
    float weight = float(arrfloat_weight[0]);
    sum += color0 * weight;
    weight_sum += weight;
    sum /= weight_sum;

    gl_FragColor = sum; 
}
