varying vec2 texc;

void main(){
    vec3 aver_color = texture2D(tex_aver,texc).rgb;
    vec3 slightblur_color = texture2D(tex_slight_blur,texc).rgb;
    float a = texture2D(tex_mask,texc).r;
    gl_FragColor = vec4(clamp(aver_color - slightblur_color + 0.5,0.0,1.0),a);
}