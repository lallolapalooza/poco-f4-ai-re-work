varying vec2 texc;

void main() {
    vec4 total_color = texture2D(tex_blur,texc);
    gl_FragColor = vec4(vec3(total_color.rgb / total_color.a),1.0);
}