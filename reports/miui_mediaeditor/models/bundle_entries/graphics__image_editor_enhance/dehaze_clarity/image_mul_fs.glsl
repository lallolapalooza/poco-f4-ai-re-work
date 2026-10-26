varying vec2 texc;
void main() {
    vec4 p_color = texture2D(tex_p,texc) * 255.0;
    vec4 I_color = texture2D(tex_I,texc) * 255.0;
    gl_FragColor = vec4(p_color.r / 255.0,p_color.r*I_color.r / 255.0,0.0,1.0);
}