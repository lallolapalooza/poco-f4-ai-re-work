varying vec2 texc;
varying vec4 sampleuv[5];
//varying vec4 sampleuv1[5];
void main() {
    texc = vec2(Pquad.x, Pquad.y);
    gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
    int i = 0;

    float rate = 0.2;
    for (i = 0; i < 5; i++) {
        sampleuv[i] = sampleDirs * r * 0.333 * rate * float(i + 1) + vec4(texc,texc);
    }  
}