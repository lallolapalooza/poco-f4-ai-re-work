varying vec2 texc;
varying vec2 texf;

void main() {
    gl_FragColor = vec4(texture2D(tex_result,texc).rgb,texture2D(tex_mask,texc).r);
}