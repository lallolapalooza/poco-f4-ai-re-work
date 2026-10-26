varying vec2 texc;

void main()
{
    float add = 1.0;
    if(coreSize == 1.0){
        add = 0.5;
    }
    vec4 maxValue=vec4(0.0);        
    float halfCoreSize=coreSize / -2.0;
    for(float y=0.0;y<coreSize;y+=add)
    {
        for(float x=0.0;x<coreSize;x+=add)
        {
            vec4 color=texture2D(tex_origin,texc+vec2((halfCoreSize+x)*offset.x,(halfCoreSize+y)*offset.y));
            maxValue=max(maxValue,color);
        }
    }
    gl_FragColor=maxValue - texture2D(tex_origin,texc);
    /* float temp = maxValue.g - texture2D(tex_origin,texc).g;
    if(maxValue.g == 1.0 && texture2D(tex_origin,texc).g == 1.0)
        gl_FragColor=vec4(0.0);
    else
        gl_FragColor=vec4(1.0); */
}