varying vec2 texc;

float getalpha(vec2 texc_eye){
    float eye_ball_color = 0.0;
    vec2 pos_eye = texc * pic_wh;
    float lefteye_dis = distance(pos_eye, left_iris_center);
    float righteye_dis = distance(pos_eye, right_iris_center);
    float left_rate = lefteye_dis / left_iris_radius;
    float right_rate = righteye_dis / right_iris_radius;
    if(left_rate <= 1.0){
      eye_ball_color = smoothstep(1.1,0.9,left_rate);
    }else if(right_rate <= 1.0){
      eye_ball_color = smoothstep(1.1,0.9,right_rate);
    }
    return clamp(texture2D(tex_eye,texc_eye).r - eye_ball_color,0.0,1.0);
}

void main() {

  gl_FragColor = vec4(texture2D(tex_makeup,texc).rgb - texture2D(tex_ori,texc).rgb + 0.5, 1.0 - getalpha(texc));
}