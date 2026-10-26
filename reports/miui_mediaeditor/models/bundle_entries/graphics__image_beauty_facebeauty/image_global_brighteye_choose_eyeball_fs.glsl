varying vec2 texc;

void main()
{
    vec2 pos = texc * wh + offset;
    vec2 pos_eye = pos * pic_wh;
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    vec3 eye_color = texture2D(tex_eyemask,texc).rgb;
    if (lefteye_dis <= left_iris_radius || righteye_dis <= right_iris_radius) {
        eye_color = vec3(0.0,0.0,0.0);
    }
    gl_FragColor = vec4(eye_color,1.0);
}