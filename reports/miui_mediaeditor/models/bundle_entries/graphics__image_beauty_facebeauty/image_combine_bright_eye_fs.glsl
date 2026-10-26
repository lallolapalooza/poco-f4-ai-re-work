varying vec2 texc;

vec3 bright_curve(vec3 color,float rate)
{
    float a1 = -0.0119802, b1 = -0.258122, c1 = 0.944906, d1 = 0.191047;
	float a2 = -0.0125641, b2 = -0.148298, c2 = 0.968544, d2 = 0.192318;
	float curveKeyPoints0 = -1.0;
	float curveKeyPoints1 = 0.5;
	float curveKeyPoints2 = 1.0;

    float x = color.r * 2.0 - 1.0;
    float new_o_r = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_r = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_r = (y + 1.0) / 2.0;
    }

    x = color.g * 2.0 - 1.0;
    float new_o_g = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_g = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_g = (y + 1.0) / 2.0;
    }

    x = color.b * 2.0 - 1.0;
    float new_o_b = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_b = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_b = (y + 1.0) / 2.0;
    }
    vec3 new_color = vec3(new_o_r,new_o_g,new_o_b);
    return color + (new_color - color) * rate;
} 

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_oriblur_slight,texc).rgb, 0.0);
        return;
    }
    float alpha = texture2D(tex_eye_mask,texc).r;
    if(alpha != 0.0){
        vec3 blur_color = texture2D(tex_oriblur_slight,texc).rgb;
        vec3 sharpen_color = texture2D(tex_sharpen,texc).rgb;
        vec3 in_circle_color = sharpen_color;
        vec3 out_circle_color = blur_color;
        if (out_circle_color.r > out_circle_color.g) {
            out_circle_color.g += 0.8*(out_circle_color.r - out_circle_color.g);
        }
        if (out_circle_color.r > out_circle_color.b) {
            out_circle_color.b += 0.8*(out_circle_color.r - out_circle_color.b);
        }
        out_circle_color = clamp(out_circle_color,0.0,1.0);
        vec3 sharpen_eyewhite_color = texture2D(tex_sharpen_eyewhite,texc).rgb;
        out_circle_color = out_circle_color * 0.7 + sharpen_eyewhite_color * 0.3;
        out_circle_color = bright_curve(out_circle_color,0.65);

        vec2 pos_eye = texc * pic_wh;
        vec2 left_iris_center = arrvec2_left_iris_center[face_idx - 1];
        vec2 right_iris_center = arrvec2_right_iris_center[face_idx - 1];
        float left_iris_radius = arrfloat_left_iris_radius[face_idx - 1];
        float right_iris_radius = arrfloat_right_iris_radius[face_idx - 1];
        float lefteye_dis = distance(pos_eye, left_iris_center);
        float righteye_dis = distance(pos_eye, right_iris_center);

        float dis_rate = 0.0;
        if (lefteye_dis <= righteye_dis) {
            dis_rate = lefteye_dis / left_iris_radius;
        } else {
            dis_rate = righteye_dis / right_iris_radius;
        }

        float eyeball_alpha = smoothstep(1.6, 0.8, dis_rate);
        vec3 origin_color = texture2D(tex_origin,texc).rgb;
        out_circle_color = mix(origin_color,out_circle_color,1.0 - eyeball_alpha);

        dis_rate = smoothstep(1.1, 0.9, dis_rate);
        vec3 result_color = in_circle_color * dis_rate + out_circle_color * (1.0 - dis_rate);
        gl_FragColor = vec4(result_color,alpha);
    }
    else
        gl_FragColor = vec4(texture2D(tex_oriblur_slight,texc).rgb,0.0);
}