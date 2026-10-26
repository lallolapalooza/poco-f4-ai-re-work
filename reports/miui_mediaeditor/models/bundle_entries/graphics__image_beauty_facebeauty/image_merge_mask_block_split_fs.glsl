varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;

        vec4 origin_color = texture2D(tex_origin,texc);
        vec4 ori_result_color = texture2D(tex_ori_result,texv);      

        vec3 result_color = mix(origin_color.rgb,ori_result_color.rgb,ori_result_color.a);
        gl_FragColor = vec4(result_color,origin_color.a);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}