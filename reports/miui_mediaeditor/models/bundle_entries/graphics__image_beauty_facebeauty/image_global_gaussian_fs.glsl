varying vec2 texc;

/* void main()
{
    float pi = 3.1415916;
    if(type == 0.0)
    {
        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            float w = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
            outcolor += texture2D(tex_origin,vec2(texc.x+i*offset.x,texc.y)).rgb * w;
            w_sum += w;
        }
        if(w_sum <= 0.001){
            gl_FragColor = texture2D(tex_origin,texc);
        }
        else{
            outcolor /= w_sum;
            gl_FragColor = vec4(outcolor,1.0);
        }
    }
    else
    {
        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            float w = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
            outcolor += texture2D(tex_origin,vec2(texc.x,texc.y+i*offset.y)).rgb * w;
            w_sum += w;
        }
        if(w_sum <= 0.001){
            gl_FragColor = texture2D(tex_origin,texc);
        }
        else{
            outcolor /= w_sum;
            gl_FragColor = vec4(outcolor,1.0);
        }
    }
} */

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

    gl_FragColor = vec4(sum); 
}