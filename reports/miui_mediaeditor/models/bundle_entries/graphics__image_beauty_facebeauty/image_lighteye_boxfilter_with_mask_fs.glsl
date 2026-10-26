varying vec2 texc;

/* void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float blur_num = arrfloat_blur_num[face_idx - 1];
    vec3 color_sum = vec3(0.0);
    float weight_sum = 0.0;
    if(type == 0.0) {
        for (float i = -1.0 * blur_num; i < blur_num; i += 1.0)
        {
            vec2 pos = texc + vec2(i,0)*offset;
            float w = clamp(texture2D(tex_mask,pos).r - texture2D(tex_mask,pos).b,0.0,1.0);
            color_sum += texture2D(tex_origin,pos).rgb * w;
            weight_sum += w;
        }
    }
    else {
        for (float i = -1.0 * blur_num; i < blur_num; i += 1.0)
        {
            vec2 pos = texc + vec2(0,i)*offset;
            float w = clamp(texture2D(tex_mask,pos).r - texture2D(tex_mask,pos).b,0.0,1.0);
            color_sum += texture2D(tex_origin,pos).rgb * w;
            weight_sum += w;
        }
    }
    if(weight_sum != 0.0){
        color_sum /= weight_sum;
        gl_FragColor = vec4(color_sum,1.0);
    }
    else{
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb,1.0); 
    }
} */

varying vec4 sampleuv[6];

float getboxalpha(vec2 tex_eye){
    float eye_ball_color = 0.0;
    int face_idx = int(texture2D(tex_label, tex_eye).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        eye_ball_color = 0.0;
    }
    vec2 pos_eye = tex_eye * pic_wh;
    vec2 left_iris_center = arrvec2_left_iris_center[face_idx - 1];
    vec2 right_iris_center = arrvec2_right_iris_center[face_idx - 1];
    float left_iris_radius = arrfloat_left_iris_radius[face_idx - 1];
    float right_iris_radius = arrfloat_right_iris_radius[face_idx - 1];
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    if (lefteye_dis <= left_iris_radius || righteye_dis <= right_iris_radius) {
        eye_ball_color = 1.0;
    }
    else{
        eye_ball_color = 0.0;
    }
    return clamp(texture2D(tex_mask,tex_eye).r - eye_ball_color,0.0,1.0);
}

vec4 BoxFliterBlurCumulate(vec4 sum, vec4 suv) {
    float pi = 3.1415916;
    vec3 color = texture2D(tex_origin, suv.xy).rgb;
    float weight = getboxalpha(suv.xy);
    sum.xyz += color * weight;
    sum.w += weight;
    color = texture2D(tex_origin, suv.zw).rgb;
    weight = getboxalpha(suv.zw);
    sum.xyz += color * weight;
    sum.w += weight;
    return sum;
}

void main() {
    float pi = 3.1415916;
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float r = arrfloat_blur_num[face_idx - 1];
    vec4 color0 = texture2D(tex_origin, texc);
    vec4 sum = vec4(0.0);
    for (int i = 0; i < 6; i++) {
        vec4 temp_sampleuv = sampleuv[i] * r + vec4(texc,texc); 
        sum = BoxFliterBlurCumulate(sum, temp_sampleuv);
    }
    float weight = clamp(texture2D(tex_mask,texc).r - texture2D(tex_mask,texc).b,0.0,1.0);
    sum.xyz += color0.xyz * weight;
    sum.w += weight;
    if(sum.w != 0.0){
		sum.xyz /= sum.w;
        gl_FragColor = vec4(sum.xyz,1.0);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
	}	
}