varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;

        vec4 origin_color = texture2D(tex_origin,texc);
        // norm
        vec4 ori_result_color = texture2D(tex_ori_result,texv); 
        vec4 aver_result_color = texture2D(tex_aver_result,texv);
        vec4 wrikle_result_color = texture2D(tex_wrikle_result,texv); 
        vec4 wrikleaver_result_color = texture2D(tex_wrikleaver_result,texv);
        vec3 norm_result = ori_result_color.rgb;
        int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
        if(face_idx > 0 && face_idx <= int(face_num)){
            float aver_rate = arrfloat_aver_rate[face_idx];
            float noacen_rate = arrfloat_noacen_rate[face_idx];
            if(noacen_rate < 0.001) {
                noacen_rate = 0.0;
            }
            else {
                noacen_rate = 1.0;
            }
            float noacen_aver_rate = max(aver_rate, noacen_rate);
            vec3 noacen_aver_color = mix(ori_result_color.rgb,aver_result_color.rgb,noacen_aver_rate);
            vec3 wrikle_noacen_aver_color = mix(wrikle_result_color.rgb,wrikleaver_result_color.rgb,noacen_aver_rate);
            float wrikle_rate = arrfloat_wrikle_rate[face_idx];
            norm_result = mix(noacen_aver_color,wrikle_noacen_aver_color,wrikle_rate);
        }

        // blur
        vec4 ori_blur_result_color = texture2D(tex_ori_blur_result,texv); 
        vec4 aver_blur_result_color = texture2D(tex_aver_blur_result,texv);
        vec4 wrikle_blur_result_color = texture2D(tex_wrikle_blur_result,texv); 
        vec4 wrikleaver_blur_result_color = texture2D(tex_wrikleaver_blur_result,texv);
        vec3 blur_result = ori_blur_result_color.rgb;
        if(face_idx > 0 && face_idx <= int(face_num)){
            float aver_rate = arrfloat_aver_rate[face_idx];
            float noacen_rate = arrfloat_noacen_rate[face_idx];
            if(noacen_rate < 0.001) {
                noacen_rate = 0.0;
            }
            else {
                noacen_rate = 1.0;
            }
            float noacen_aver_rate = max(aver_rate, noacen_rate);
            vec3 noacen_aver_color = mix(ori_blur_result_color.rgb,aver_blur_result_color.rgb,noacen_aver_rate);
            vec3 wrikle_noacen_aver_color = mix(wrikle_blur_result_color.rgb,wrikleaver_blur_result_color.rgb,noacen_aver_rate);
            float wrikle_rate = arrfloat_wrikle_rate[face_idx];
            blur_result = mix(noacen_aver_color,wrikle_noacen_aver_color,wrikle_rate);
        }
        
        vec3 result_color = mix(origin_color.rgb,norm_result,ori_result_color.a);
        result_color = mix(result_color,blur_result,ori_blur_result_color.a * blur_rate * blur_rate_scale);
        gl_FragColor = vec4(result_color,origin_color.a);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}