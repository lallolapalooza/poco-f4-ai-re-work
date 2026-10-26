varying vec2 texc;
varying vec2 texf;

void main(){
    int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
    float aver_rate = 0.0;
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb, 1.0);
        return;
    }
    else{
        aver_rate = arrfloat_aver_rate[face_idx];
    }
    vec3 origin_color = texture2D(tex_origin,texc).rgb;
    vec3 acenaver_color = texture2D(tex_acenaver,texc).rgb;
    gl_FragColor = vec4(mix(origin_color,acenaver_color,aver_rate),1.0);
}