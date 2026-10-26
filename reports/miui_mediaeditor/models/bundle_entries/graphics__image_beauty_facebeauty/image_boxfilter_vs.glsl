varying vec2 texc;
varying vec4 sampleuv[6];
void main() {
    texc = vec2(Pquad.x, Pquad.y);
    gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
    int i = 0;

    float rate = 0.16667;
    for (i = 0; i < 6; i++) {
        sampleuv[i] = sampleDirs * rate * float(i + 1);
    }  
}