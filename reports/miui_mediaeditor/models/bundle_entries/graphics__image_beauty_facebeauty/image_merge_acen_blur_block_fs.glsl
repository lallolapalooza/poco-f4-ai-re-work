varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;
        vec4 origin_color = texture2D(tex_origin,texc);
        vec4 acenblur_color = texture2D(tex_acenblur,texv);

        /* vec3 aver_result_color = texture2D(tex_aver_result,texf).rgb;
        vec3 origin_resulr_color = texture2D(tex_origin_result,texf).rgb;
        int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
        if(face_idx > 0 && face_idx <= int(face_num)){
            float aver_rate = arrfloat_aver_rate[face_idx];
            float noacen_rate = arrfloat_noacen_rate[face_idx];
            if(noacen_rate < 0.5) {
                noacen_rate = 0.0;
            }
            else {
                noacen_rate = 1.0;
            }
            float temp = max(aver_rate, noacen_rate);
            acenblur_color.rgb += (origin_resulr_color - aver_result_color) * (1.0 - temp) *  0.25;
        } */

        vec4 mask_color = texture2D(tex_protect_manual_mask,texf);
        float blur_alpha = texture2D(tex_blur,texf).a;
		float zhi_alpha = mask_color.a;
		if(zhi_alpha < 1.0){
            blur_alpha = 1.0;
        }
        //blur_alpha *= mask_color.r;
        gl_FragColor = vec4(mix(origin_color.rgb,acenblur_color.rgb,acenblur_color.a * blur_alpha),1.0);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}