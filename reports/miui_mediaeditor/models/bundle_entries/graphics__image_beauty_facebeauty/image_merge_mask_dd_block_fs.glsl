varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;
        vec4 origin_color = texture2D(tex_origin,texc);
        vec4 result_color = texture2D(tex_result,texv);
        vec4 resultblur_color = texture2D(tex_resultblur,texv);
        float darkeye_mask_color = texture2D(tex_darkeye_depattern_mask,texf).r;
        float depattern_mask_color = texture2D(tex_darkeye_depattern_mask,texf).g;
        int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
        if(face_idx <= 0 || face_idx > int(face_num)){
            gl_FragColor = origin_color;
            return;
        }
        result_color = mix(result_color,resultblur_color,blur_rate);
        vec3 outcolor = origin_color.rgb;
        float darkeye_rate = arrfloat_darkeye_rate[face_idx];
        if(darkeye_rate > 0.001){
            outcolor = mix(outcolor,result_color.rgb,darkeye_rate * result_color.a * darkeye_mask_color);
        }
        float depattern_rate = arrfloat_depattern_rate[face_idx];
        if(depattern_rate > 0.001){
            outcolor = mix(outcolor,result_color.rgb,depattern_rate * result_color.a * depattern_mask_color);
        }
        gl_FragColor = vec4(outcolor,origin_color.a);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}