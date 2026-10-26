varying vec2 texc;
void main() {
    vec4 ab_color = texture2D(tex_ab,texc) * 255.0;
    vec4 I_color = texture2D(tex_I,texc) * 255.0;
    vec4 in_color = texture2D(tex_in,texc) * 255.0;
    I_color = vec4(in_color.rgb,I_color.r);
    //gl_FragColor = vec4(vec3(clamp((ab_color.r * I_color.r + ab_color.g)/255.0,0.0,1.0)),1.0);
    gl_FragColor = clamp((vec4(ab_color.r) * I_color + vec4(ab_color.g))/255.0,0.0,1.0);

}