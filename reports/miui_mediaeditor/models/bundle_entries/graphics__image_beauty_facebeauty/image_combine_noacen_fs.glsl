varying vec2 texc;
varying vec2 texf;

float blendSoftLight(float base, float blend) { 
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend); 
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend); 
  return mix(color1, color2, step(0.5, blend)); 
} 

float tuoyuandis(vec2 v,vec2 r){
    float dis = (v.x * v.x)/(r.x * r.x) + ((v.y * v.y)/(r.y * r.y)); 
    return dis;
}

 void main() {
    vec3 iColor = texture2D(tex_inputImage, texc).rgb; 
    vec3 colorRepair = texture2D(tex_inputImage_blur, texc).rgb; 
    float random_vec_x = manualPoint_vec.x + 1.0 * manualPoint_r.x * cos(random_single);
    float random_vec_y = manualPoint_vec.y + 1.0 * manualPoint_r.y * sin(random_single);
    vec2 random_vec = vec2(random_vec_x,random_vec_y) + texc - manualPoint_vec;
    vec2 random_vef = (random_vec * f_dim - b_offset) / b_dim;

    vec3 highpass_color = texture2D(tex_inputImage_highpass,random_vef).rgb;
    vec3 result_color = colorRepair + 1.0 * (highpass_color * 2.0 - 1.0);
    result_color = clamp(result_color,0.0,1.0);

    float dis = tuoyuandis(texf - manualPoint_vec,manualPoint_r);
    dis = 1.0 - clamp(dis,0.0,1.0);
    gl_FragColor = vec4(mix(iColor, result_color, dis), 1.0);
}
/* void main(){
    int id = 0;
    float dis_min = 100.0;
    for(int i = 0; i < int(manualAcenPointv_num + 0.1); ++i){
        vec2 manualPoint_vec = arrvec2_manualAcenPointv_point[i];
        float dis = distance(texf,manualPoint_vec);
        if(dis < dis_min){
            dis_min = dis;
            id = i;
        } 
    }
    vec2 manualPoint_vec = arrvec2_manualAcenPointv_point[id];
    vec2 manualPoint_r = arrvec2_manualAcenPointv_radius[id];
    float random_single = arrfloat_manualAcenPointv_single[id];
    vec3 iColor = texture2D(tex_inputImage, texf).rgb; 
    vec3 colorRepair = texture2D(tex_inputImage_blur, texf).rgb; 
    float random_vec_x = manualPoint_vec.x + 1.0 * manualPoint_r.x * cos(random_single);
    float random_vec_y = manualPoint_vec.y + 1.0 * manualPoint_r.y * sin(random_single);
    vec2 random_vec = vec2(random_vec_x,random_vec_y) + texf - manualPoint_vec;

    vec3 highpass_color = texture2D(tex_inputImage_highpass,random_vec).rgb;
    vec3 result_color = colorRepair + 1.0 * (highpass_color * 2.0 - 1.0);
    result_color = clamp(result_color,0.0,1.0);

    gl_FragColor = vec4(mix(iColor, result_color, texture2D(tex_mask,texf).r), 1.0);

} */