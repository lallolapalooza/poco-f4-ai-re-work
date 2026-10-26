varying vec2 texc;
void main() {
    vec4 box_color = texture2D(tex_box,texc) * 255.0;
    float color = box_color.g - box_color.r * box_color.r;
    gl_FragColor = vec4(color / 255.0);

}