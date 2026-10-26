varying vec2 texc;

void main()
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
}