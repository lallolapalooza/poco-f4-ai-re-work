varying vec2 texc;

vec3 saturation_change(vec3 source, float saturation) {
    vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);
    float luminance = dot(source.rgb, luminanceWeighting);
    vec3 greyScaleColor = vec3(luminance);
    return mix(greyScaleColor, source.rgb, saturation);
}

vec3 brightness_contrast_change(vec3 source,float brightness,float contrast){
    float PI = 3.1415926;
	float B = brightness / 255.0;
	float c = contrast / 255.0;
	float k = tan((45.0 + 44.0 * c) / 180.0 * PI);
	vec3 colorrbg = ((source*255.0 - 127.5 * (1.0 - B)) * k + 127.5 * (1.0 + B)) / 255.0;
    return colorrbg;
}

void main()
{
    vec3 source_color = texture2D(tex_source,texc).rgb;
    vec3 destination_color = texture2D(tex_destination,texc).rgb;
    vec3 outcolor = source_color + (destination_color * 2.0 - 1.0);
    outcolor = mix(source_color,outcolor,rate);
    if(type < 0.5){
        vec3 outcolor1 = outcolor + (destination_color * 2.0 - 1.0);
        outcolor1 = mix(outcolor,outcolor1,rate);
        outcolor = saturation_change(outcolor1,1.15);
        //outcolor = brightness_contrast_change(outcolor,0.0,27.0);
        //outcolor = outcolor1;
    }
    gl_FragColor = vec4(outcolor,1.0);
}