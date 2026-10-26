varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(0.0,0.0,0.0,1.0);
        return;
    }
    float r = texture2D(tex_averblur_eye_mask,texc).r;
    float g = 0.0;
    float b = 0.0;
    vec2 pos_eye = texc * pic_wh;
    vec2 left_iris_center = arrvec2_left_iris_center[face_idx - 1];
    vec2 right_iris_center = arrvec2_right_iris_center[face_idx - 1];
    float left_iris_radius = arrfloat_left_iris_radius[face_idx - 1];
    float right_iris_radius = arrfloat_right_iris_radius[face_idx - 1];
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    if (lefteye_dis <= left_iris_radius || righteye_dis <= right_iris_radius) {
        g = 1.0;
    }
    left_iris_radius *= 1.2;
    right_iris_radius *= 1.2;
    lefteye_dis = distance(pos_eye, left_iris_center);
    righteye_dis = distance(pos_eye, right_iris_center);
    if (lefteye_dis <= left_iris_radius || righteye_dis <= right_iris_radius) {
        b = 1.0;
    }
    gl_FragColor = vec4(r,g,b,1.0);
}