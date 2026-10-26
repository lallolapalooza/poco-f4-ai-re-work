varying vec2 texc;
varying vec2 texf;

void main(){
    /* vec3 outcolor = vec3(0.0);
    for(float i = 0.0;i < circle_num;i += 1.0){
        int num = (int)(i+0.1);
        vec2 manualPoint_vec = arrvec2_circlev[num];
        vec2 manualPoint_r = arrvec2_circler[num];
        vec2 texc_dis = texc - manualPoint_vec;
        float dis = (texc_dis.x * texc_dis.x)/(manualPoint_r.x * manualPoint_r.x) + 
        ((texc_dis.y * texc_dis.y)/(manualPoint_r.y * manualPoint_r.y)); 
        if(dis <= 1.0){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    gl_FragColor = vec4(outcolor,1.0); */
    vec3 outcolor = vec3(0.0);
    if (texc.x >= offset_big.x && texc.x <= offset_big.x + wh_big.x && texc.y >= offset_big.y &&
      texc.y <= offset_big.y + wh_big.y) {
        outcolor.g = 1.0;
        if (texc.x >= offset_small.x && texc.x <= offset_small.x + wh_small.x && texc.y >= offset_small.y &&
            texc.y <= offset_small.y + wh_small.y) {
            outcolor.g = 0.0;
            vec2 texc_dis = texc - manualPoint_vec;
            float dis = (texc_dis.x * texc_dis.x)/(manualPoint_r_x * manualPoint_r_x) + 
            ((texc_dis.y * texc_dis.y)/(manualPoint_r_y * manualPoint_r_y)); 
            if(dis <= 1.0){
                outcolor.r = 1.0;
                outcolor.g = 1.0;
            }
        }
    }
    gl_FragColor = vec4(outcolor,1.0);
    /* if (texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y &&
      texc.y <= offset.y + wh.y) {
          outcolor.g = 
        vec2 texc_dis = texc - manualPoint_vec;
        float dis = (texc_dis.x * texc_dis.x)/(manualPoint_r_x * manualPoint_r_x) + 
        ((texc_dis.y * texc_dis.y)/(manualPoint_r_y * manualPoint_r_y)); 
        if(dis <= 1.0){
            gl_FragColor = vec4(1.0,1.0,1.0,1.0);
        }
        else{
            gl_FragColor = vec4(0.0,0.0,0.0,1.0);
        }
    } */
    
}