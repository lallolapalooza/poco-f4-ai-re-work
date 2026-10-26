varying vec2 texc;

void main()
{
    if(texture2D(tex_brighteye_mask,texc).r > 0.0)
    {
        vec3 color_sum = vec3(0.0);
        float num_sum = 0.0;
        for (float i = -1.0 * blur_num; i < blur_num; i += 1.0)
        {
            for (float j = -1.0 * blur_num; j < blur_num; j += 1.0)
            {
                vec2 pos = texc + vec2(i,j)*offset;
                if(texture2D(tex_eyeball,texc).r == 0.0)
                {
                    color_sum += texture2D(tex_origin,pos).rgb;
                    num_sum++;
                }
            }
        }
        color_sum /= num_sum;
        gl_FragColor = vec4(color_sum,1.0);
    }
    else
        gl_FragColor = texture2D(tex_origin,texc);
}