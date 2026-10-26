varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;
        vec3 origin_color = texture2D(tex_origin,texc).rgb;
        vec3 blur_color = texture2D(tex_blur,texc).rgb;
        vec3 highpass_color = clamp(origin_color - blur_color + 0.5,0.0,1.0);
        vec4 aver_color = texture2D(tex_aver,texv);
        vec3 outcolor = aver_color.rgb + (highpass_color * 2.0 - 1.0);
        outcolor = mix(aver_color.rgb,outcolor, 0.55);
        gl_FragColor = vec4(mix(origin_color,outcolor,aver_color.a * aver_rate),1.0);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}