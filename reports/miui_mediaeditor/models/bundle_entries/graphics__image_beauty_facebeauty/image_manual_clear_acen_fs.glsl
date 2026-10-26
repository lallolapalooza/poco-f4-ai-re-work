varying vec2 texc;
varying vec2 texf;

float tuoyuandis(vec2 v,vec2 r){
    float dis = (v.x * v.x)/(r.x * r.x) + (v.y * v.y)/(r.y * r.y); 
    return dis;
}

void main(){
    float pi = 3.1415916;
    if(type < 0.5){
        float sum_i = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            vec2 temp_texc = vec2(texc.x+i*offset.x,texc.y);
            vec2 temp_texf = (temp_texc * b_dim + b_offset) / f_dim;
            float dis_temp = tuoyuandis(temp_texf - manualPoint_vec, manualPoint_r);
            if(dis_temp > 1.0) {
                sum_i += 1.0;
            }
        }
		float real_r = (r / 2.0) + (r / 2.0) - (sum_i / 2.0);
		real_r *= 2.0;
        float sigma_t = 0.3 * ((real_r - 1.0) * 0.5 - 1.0) + 0.8;

        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        float j = -r/2.0;
        for(float i=-real_r/2.0;i<=real_r/2.0;i+=1.0)
        {
            vec2 temp_texc = vec2(texc.x+i*offset.x,texc.y);
            vec2 temp_texf = (temp_texc * b_dim + b_offset) / f_dim;
            float dis_temp = tuoyuandis(temp_texf - manualPoint_vec, manualPoint_r);
            if(dis_temp > 1.0) {
                float w = 1.0/(sigma_t*sqrt(2.0*pi)) * exp(-1.0 * j * j/(2.0*sigma_t*sigma_t));
                outcolor += texture2D(tex_origin,temp_texc).rgb * w;
                w_sum += w;
                j += 1.0;
            }
        }
		if(w_sum != 0.0){
            outcolor /= w_sum;
            gl_FragColor = vec4(outcolor,1.0);
        }
        else{
            gl_FragColor = texture2D(tex_origin,texc);
        }
    }
    else{
        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        for(float i=-r/2.0;i<=r/2.0;i+=1.0)
        {
            vec2 temp_texc = vec2(texc.x,texc.y+i*offset.y);
            float w = 1.0/(sigma*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma*sigma));
            outcolor += texture2D(tex_origin,temp_texc).rgb * w;
            w_sum += w;
        }
        if(w_sum != 0.0){
            outcolor /= w_sum;
            gl_FragColor = vec4(outcolor,1.0);
        }
        else{
            gl_FragColor = texture2D(tex_origin,texc);
        }
    }
}

/* void main() {

    float mask_alpha = texture2D(tex_mask,texf).r;
    if(mask_alpha < 0.001){
        gl_FragColor = texture2D(tex_origin,texf);
        return;
    }
    float pi = 3.1415916;
    if(type < 0.5){
        float r = 0.0;
        float dis_min = 100.0;
        for(int i = 0; i < int(manualAcenPointv_num + 0.1); ++i){
            vec2 manualPoint_vec = arrvec2_manualAcenPointv_point[i];
            float manualPoint_r = arrvec2_manualAcenPointv_radius[i].x;
            float dis = distance(texf,manualPoint_vec);
            if(dis < dis_min){
                dis_min = dis;
                r = manualPoint_r;
            }
        }
        r/=offset.x;
        float sum_left = 0.0;
        vec2 texf_temp = vec2(texf.x-sum_left*offset.x,texf.y);
        while(!(texture2D(tex_mask,texf_temp).r < 0.3 || texf_temp.x < 0.0 || texf_temp.x > 1.0)){
            texf_temp = vec2(texf.x-sum_left*offset.x,texf.y);           
            sum_left += 1.0;
        }

        float sum_right = 0.0;
        texf_temp = vec2(texf.x+sum_right*offset.x,texf.y);
        while(!(texture2D(tex_mask,texf_temp).r < 0.3 || texf_temp.x < 0.0 || texf_temp.x > 1.0)){
            texf_temp = vec2(texf.x+sum_right*offset.x,texf.y);
            sum_right += 1.0;
        }
        
        float dev = 0.0;
        if(sum_left > sum_right){
            sum_right += (sum_left - sum_right) / 2.0;
            sum_left -= (sum_left - sum_right) / 2.0;
        }
        else{
            sum_right -= (sum_right - sum_left) / 2.0;
            sum_left += (sum_right - sum_left) / 2.0;
        }
        sum_right += r;
        sum_left += r;

        float sigma_t = 0.3 * ((2.0 * r - 1.0) * 0.5 - 1.0) + 0.8;
        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        float j = -r;
        for(float i=-sum_left;i<=sum_right;i+=1.0)
        {
            vec2 temp_texf = vec2(texf.x+i*offset.x,texf.y);
            if(texture2D(tex_mask,temp_texf).r < 0.3) {
                float w = 1.0/(sigma_t*sqrt(2.0*pi)) * exp(-1.0 * j * j/(2.0*sigma_t*sigma_t));
                outcolor += texture2D(tex_origin,temp_texf).rgb * w;
                w_sum += w;
                j += 1.0;
            }
        }
		if(w_sum != 0.0)
            outcolor /= w_sum;
        gl_FragColor = vec4(outcolor,1.0);
    }
    else{
        float r = 0.0;
        float dis_min = 100.0;
        for(int i = 0; i < int(manualAcenPointv_num + 0.1); ++i){
            vec2 manualPoint_vec = arrvec2_manualAcenPointv_point[i];
            float manualPoint_r = arrvec2_manualAcenPointv_radius[i].y;
            float dis = distance(texf,manualPoint_vec);
            if(dis < dis_min){
                dis_min = dis;
                r = manualPoint_r;
            }
        }
        r/=offset.y;
        float sigma_t = 0.3 * ((2.0 * r - 1.0) * 0.5 - 1.0) + 0.8;
        vec3 outcolor = vec3(0.0);
        float w_sum = 0.0;
        for(float i=-r;i<=r;i+=1.0)
        {
            vec2 temp_texf = vec2(texf.x,texf.y+i*offset.y);
            float w = 1.0/(sigma_t*sqrt(2.0*pi)) * exp(-1.0 * i * i/(2.0*sigma_t*sigma_t));
            outcolor += texture2D(tex_origin,temp_texf).rgb * w;
            w_sum += w;
        }
        if(w_sum != 0.0)
            outcolor /= w_sum;
        gl_FragColor = vec4(outcolor,1.0);
    }
} */