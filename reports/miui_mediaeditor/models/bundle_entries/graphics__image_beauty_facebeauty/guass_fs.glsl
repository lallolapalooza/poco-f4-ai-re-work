void main()
{
    vec3 sum = texture2D(tex, vUV).rgb;
    if(is_blur_mask < 0.5){
        sum += texture2D(tex, textureShift_1.xy).rgb; 
        sum += texture2D(tex, textureShift_1.zw).rgb; 
        /* sum += texture2D(tex, textureShift_2.xy).rgb; 
        sum += texture2D(tex, textureShift_2.zw).rgb;  */
        sum += texture2D(tex, textureShift_3.xy).rgb; 
        sum += texture2D(tex, textureShift_3.zw).rgb; 
        /* sum += texture2D(tex, textureShift_4.xy).rgb; 
        sum += texture2D(tex, textureShift_4.zw).rgb; 
        sum = sum * 0.1111; */
        sum = sum * 0.2;
    }
    else{
        sum += texture2D(tex, textureShift_0.xy).rgb; 
        sum += texture2D(tex, textureShift_0.zw).rgb; 
        sum += texture2D(tex, textureShift_1.xy).rgb; 
        sum += texture2D(tex, textureShift_1.zw).rgb;
        sum = sum * 0.2;
    }
    gl_FragColor = vec4(sum, 1.0);   
}